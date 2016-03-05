public class Suit implements Comparable
{
   static public final Suit spade = new Suit(4, "spades");
   static public final Suit heart = new Suit(3, "hearts");
   static public final Suit diamond = new Suit(2, "diamonds");
   static public final Suit club = new Suit(1, "club");
   private int order;
   private String name;
   
   private Suit(int order, String name)
   {
      this.name = name;
      this.order  =  order;
   }
   public int compareTo(Object other)
   {
      if(! (other instanceof Suit))
         throw new IllegalArgumentException("Parameter must be a Suit");
      Suit otherSuit = (Suit)other;
      return order - otherSuit.order;
   }
   
   public String toString()
   {
      return name;
   }
}