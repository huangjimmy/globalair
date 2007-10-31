class PromotionService
{
	def final_discount = 0.0
	def promotions = [new Promotion(), new Promotion2(), new Promotion3()]
	
	def getPromotion(int year, int month, int day, String ffp)
	{
		def discount = -0.1
		def p
		promotions.each
		{
			if(discount < it.getDiscount(year, month, day, ffp))
			{
				discount = it.getDiscount(year, month, day, ffp)
				p = it;
			}
		}
		
		final_discount = discount
		return p;
	}
	
	def getDiscount()
	{
		return final_discount
	}
}