class PromotionService
{
	def final_discount = 0.0
	def promotions = [new Promotion(), new Promotion2(), new Promotion3()]
	
	def getPromotion(int year, int month, int day, String ffp)
	{
		final_discount = -0.1
		def p
		promotions.each
		{
			if(final_discount < it.getDiscount(year, month, day, ffp))
			{
				final_discount = it.getDiscount(year, month, day, ffp)
				println "\t"+year+" "+month+" "+day+" "+ffp
				println "\t"+it+"\t"+final_discount
				p = it;
			}
		}
		println final_discount
		println p

		return p;
	}
	
	def getDiscount()
	{
		return final_discount
	}
}