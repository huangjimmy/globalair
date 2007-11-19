class TestService {
	
	static expose=['xfire']

	boolean transactional = true
	
	def serviceMethod() {
		// TODO
	}
	
	String sayHello(String yourname)
	{
		return "Hello "+yourname+"!"
	}
}

