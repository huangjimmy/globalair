<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">

    <xsl:for-each select="strategydefs/strategy">
      
      <xsl:choose>
        <xsl:when test="@type = 't1'">
          根据会员等级
          <xsl:for-each select="rule">
            <xsl:value-of select="@arg1"/>
            <BR/>
          </xsl:for-each>
        </xsl:when>
        <xsl:when test="@type = 't2'">
          根据某些类型/时间段打折
          <xsl:for-each select="rule">
            <xsl:value-of select="."/>
            <xsl:value-of select="@arg1"/>
            <xsl:value-of select="@arg2"/>
            <xsl:value-of select="@arg3"/>
            <BR/>
          </xsl:for-each>
        </xsl:when>
        <xsl:when test="@type = 't3'">
          根据淡季/旺季时期打折
          <xsl:for-each select="/rule">
            <xsl:value-of select="@arg1"/>
            <BR/>
          </xsl:for-each>
        </xsl:when>
        <xsl:when test="@type = 't4'">
          除部分不参加活动外，其他统一折扣
          <xsl:for-each select="rule">
            <xsl:value-of select="@arg1"/>
            <BR/>
          </xsl:for-each>
        </xsl:when>
        <xsl:when test="@type = 't5'">
          指定时间段内，前N名享受特别折扣
          <xsl:for-each select="rule">
            <xsl:value-of select="@arg1"/>
            <BR/>
          </xsl:for-each>
        </xsl:when>
        <xsl:otherwise >
          <xsl:for-each select="rule">
            <xsl:value-of select="@arg1"/>
            <xsl:value-of select="@arg2"/>
            <xsl:value-of select="@arg3"/>
            <BR/>
          </xsl:for-each>
        </xsl:otherwise>
      </xsl:choose>

    </xsl:for-each>


  </xsl:template>
</xsl:stylesheet>