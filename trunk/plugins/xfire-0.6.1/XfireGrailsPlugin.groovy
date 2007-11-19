import org.codehaus.groovy.grails.plugins.support.*
import org.grails.xfire.*
import org.grails.xfire.ServiceFactoryBean
import org.codehaus.groovy.grails.commons.GrailsClassUtils

class XfireGrailsPlugin {

	def version = '0.6.1'
	def dependsOn = [services:'0.6']
	def loadAfter = ['services']
	
	def watchedResources = ["**/grails-app/services/*Service.groovy"]
	
	def doWithSpring = {
	
		"xfire.serviceRegistry"(org.codehaus.xfire.service.DefaultServiceRegistry) { bean->			
			bean.getBeanDefinition().setSingleton(true)
		}
		
		"xfire.transportManager"(org.codehaus.xfire.transport.DefaultTransportManager){ bean->
			bean.getBeanDefinition().setSingleton(true)
			bean.getBeanDefinition().setInitMethodName("initialize")
			bean.getBeanDefinition().setDestroyMethodName("dispose")
		}
				
		"xfire"(org.codehaus.xfire.DefaultXFire, 
		         ref("xfire.serviceRegistry"), 
				 ref("xfire.transportManager")) { bean ->			
			bean.getBeanDefinition().setSingleton(true)
		}
		
		"xfire.typeMappingRegistry"(org.codehaus.xfire.aegis.type.DefaultTypeMappingRegistry){ bean ->
			bean.getBeanDefinition().setSingleton(true)
			bean.getBeanDefinition().setInitMethodName("createDefaultMappings");
		}

		"xfire.aegisBindingProvider"(org.codehaus.xfire.aegis.AegisBindingProvider,
			ref("xfire.typeMappingRegistry")) { bean ->			
			bean.getBeanDefinition().setSingleton(true)
		}

		"xfire.serviceFactory"(org.codehaus.xfire.service.binding.ObjectServiceFactory,
			ref("xfire.transportManager"), ref("xfire.aegisBindingProvider")) { bean ->			
			bean.getBeanDefinition().setSingleton(true)
		}
		
		"xfire.servletController"(org.codehaus.xfire.transport.http.XFireServletController,
			ref("xfire")) { bean ->
			bean.getBeanDefinition().setSingleton(true)
		}
		
		"grails.xfire"(org.grails.xfire.ServiceFactoryBean, "grails.xfire") { bean ->
			bean.getBeanDefinition().setInitMethodName("initialize")
			transportManager = ref("xfire.transportManager")
			grailsApplication = ref("grailsApplication", true)
		}
		
		// println "passed grails.xfire bean"
		
//<bean id="grails.xfire"
//    class="org.grails.xfire.ServiceFactoryBean"
//    init-method="initialize">
//    <constructor-arg index="0" value="grails.xfire" />
//    <property name="transportManager" ref="xfire.transportManager" />
//    <property name="grailsApplication" ref="grailsApplication" />
//</bean>	
		if(application.serviceClasses) {
		application.serviceClasses.each { service ->
			def serviceClass = service.getClazz()
			def exposeList = GrailsClassUtils.getStaticPropertyValue(serviceClass, 'expose')
			if(exposeList!=null && exposeList.contains('xfire')) {
				println '>>>> exposing ' + service.shortName
				def sName = service.propertyName.replaceFirst("Service","")
				// <bean name="myService" class="org.codehaus.xfire.spring.ServiceBean">
				"${sName}XFire"(org.grails.xfire.ServiceBean){
					//   <property name="xfire" ref="xfire"/>
					xfire = ref("xfire")
					//   <property name="serviceBean" ref="myService"/>
					serviceBean = ref("${service.propertyName}")
					//   <property name="serviceClass" value="MyService"/>
					serviceClass = service.getClazz()
					//   <property name="serviceFactory" ref="grails.xfire"/>
					serviceFactory = ref("grails.xfire")
				}				
			}
		}		
		}
	}   
	
	def doWithApplicationContext = { applicationContext ->
		// TODO Implement post initialization spring config (optional)		
	}
	
	def doWithWebDescriptor = { xml ->
		// add xfire configuration to context
		// xfire config file should be placed after the application context file or it doesn't work
		//def contextConfigParam = xml.'context-param'.find { it.'param-name' == 'contextConfigLocation' }
		//contextConfigParam.'param-value' + {
		//	'param-value'("classpath:org/codehaus/xfire/spring/xfire.xml")			
		//}
	   
		def filters = xml.filter
		def filterMappings = xml.'filter-mapping'
		def servlets = xml.servlet
		def servletMappings = xml.'servlet-mapping'
		
		// define hibernate's OpenSessionInViewFilter
		def hibernateFilter = 'hibernateFilter'			                               
		filters[filters.size()-1] + {
			filter {
				'filter-name'(hibernateFilter)
				'filter-class'('org.grails.xfire.OpenSessionInViewFilter')
			}
		}		
		filterMappings[filterMappings.size()-1] + {
			'filter-mapping' {						
				'filter-name'(hibernateFilter)
				'url-pattern'("/services/*")						
			}		
		}
		
		def xfireServlet = 'XFireServlet'
		servlets[servlets.size()-1] + {
			servlet {
				'servlet-name'(xfireServlet)
				'servlet-class'('org.grails.xfire.XFireSpringServlet')
				'load-on-startup'(1)
			}
		}		
		servletMappings[servletMappings.size()-1] + {
			'servlet-mapping' {						
				'servlet-name'(xfireServlet)
				'url-pattern'("/services/*")						
			}		
		}		
		servletMappings[servletMappings.size()-1] + {
			'servlet-mapping' {						
				'servlet-name'(xfireServlet)
				'url-pattern'("/servlet/XFireServlet/*")						
			}		
		}				
	}	        
	
	def onChange = { event ->
		// TODO Implement code that is executed when this class plugin class is changed  
		// the event contains: event.application and event.applicationContext objects
	}                                                                                  
	def onApplicationChange = { event ->
		// TODO Implement code that is executed when any class in a GrailsApplication changes
		// the event contain: event.source, event.application and event.applicationContext objects
	}
}
