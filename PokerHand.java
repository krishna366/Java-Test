import java.util.*;
    
public class PokerHand
{      
    public enum Result { TIE, WIN, LOSS } 
    
    private String[] cards;
    private int[] cardValues;
    public String[] getCards(){
      return cards;
    }
    public int[] getCardValues(){
      return cardValues;
    }
  
    private int handStatus;
    public int getHandStatus(){
      return handStatus;
    }
  
    public static final int  STRAIGHT_FLUSH = 1;
    public static final int  FOUR_A_KIND = 2;
    public static final int  FULL_HOUSE = 3;
    public static final int  FLUSH = 4;
    public static final int  STRAIGHT = 5;
    public static final int  THREE_A_KIND = 6;
    public static final int  TWO_PAIR = 7;
    public static final int  ONE_PAIR = 8;
    public static final int  HIGH_CARD = 9;
    
    public static int cardToInt(String s){
          char c1 = s.charAt(0);
          int v1 = 0;
          if(c1 == '2') v1 = 2;
          if(c1 == '3') v1 = 3; 
          if(c1 == '4') v1 = 4; 
          if(c1 == '5') v1 = 5; 
          if(c1 == '6') v1 = 6; 
          if(c1 == '7') v1 = 7; 
          if(c1 == '8') v1 = 8; 
          if(c1 == '9') v1 = 9; 
          if(c1 == 'T') v1 = 10; 
          if(c1 == 'J') v1 = 11; 
          if(c1 == 'Q') v1 = 12; 
          if(c1 == 'K') v1 = 13; 
          if(c1 == 'A') v1 = 14;
      
          return v1;
    }
  
    private boolean isStraightFlush(){
      boolean sameSuite = false;
      boolean seqFlag = false;
      if(cards[0].charAt(1) == cards[1].charAt(1) &&
         cards[1].charAt(1) == cards[2].charAt(1) &&
        cards[2].charAt(1) == cards[3].charAt(1) &&
        cards[3].charAt(1) == cards[4].charAt(1)) sameSuite = true;
      else sameSuite = false;
      if(cardValues[0]+1 == cardValues[1] &&
         cardValues[1]+1 == cardValues[2] &&
         cardValues[2]+1 == cardValues[3] &&
         cardValues[3]+1 == cardValues[4]
        ) seqFlag=true;
      else seqFlag=false;
      
      return sameSuite && seqFlag;
    }
    public Result tieBreakStraightFlush(PokerHand hand){
      int[] cv = hand.getCardValues();
      if(cardValues[4] > cv[4]) return Result.WIN;
      if(cardValues[4] < cv[4]) return Result.LOSS;
      return Result.TIE;
    }
  
    private boolean isFourAKind(){
      return (cardValues[0] == cardValues[1] &&
         cardValues[1] == cardValues[2] &&
         cardValues[2] == cardValues[3]) ||
        (cardValues[1] == cardValues[2] &&
         cardValues[2] == cardValues[3] &&
         cardValues[3] == cardValues[4]);
    }
  
    public Result tieBreakFourAKind(PokerHand hand){
      int val1 = 0;
      int val2 = 0;
      
      if(cardValues[0] == cardValues[1] &&
         cardValues[1] == cardValues[2] &&
         cardValues[2] == cardValues[3]) val1 = cardValues[0];
      else val1 = cardValues[1];
      
      int[] cv = hand.getCardValues();
  
      if(cv[0] == cv[1] &&
         cv[1] == cv[2] &&
         cv[2] == cv[3]) val2 = cv[0];
      else val2 = cv[1];
     
      if(val1 > val2) return Result.WIN;
      if(val1 < val2) return Result.LOSS;
      return tieBreakHighCard(hand);
    }
  
    private boolean isFullHouse(){
      return (cardValues[0] == cardValues[1] &&
         cardValues[1] == cardValues[2] &&
         cardValues[3] == cardValues[4]) ||
        (cardValues[0] == cardValues[1] &&
         cardValues[2] == cardValues[3] &&
         cardValues[3] == cardValues[4]);
    }
    public Result tieBreakFullHouse(PokerHand hand){
      int val1 = 0;
      int val2 = 0;
      
      int[] cv = hand.getCardValues();
      
      val1 = cardValues[2];
      val2 = cv[2];
      
      if(val1 > val2) return Result.WIN;
      if(val1 < val2) return Result.LOSS;
      
      if(cardValues[0] == cardValues[1] &&
         cardValues[1] == cardValues[2] &&
         cardValues[3] == cardValues[4]) val1 = cardValues[3];
      else val1 = cardValues[0];
      
       if(cv[0] == cv[1] &&
         cv[1] == cv[2] &&
         cv[3] == cv[4]) val2 = cv[3];
      else val2 = cv[0];
      
       if(val1 > val2) return Result.WIN;
      if(val1 < val2) return Result.LOSS;
      return Result.TIE;
    }
  
    private boolean isFlush(){
       if(cards[0].charAt(1) == cards[1].charAt(1) &&
         cards[1].charAt(1) == cards[2].charAt(1) &&
        cards[2].charAt(1) == cards[3].charAt(1) &&
        cards[3].charAt(1) == cards[4].charAt(1)) return true;
      else return false;
    }
    public Result tieBreakFlush(PokerHand hand){
      return tieBreakHighCard(hand);
    }
  
    private boolean isStraight(){
      if(cardValues[0]+1 == cardValues[1] &&
         cardValues[1]+1 == cardValues[2] &&
         cardValues[2]+1 == cardValues[3] &&
         cardValues[3]+1 == cardValues[4]
        )  return true;
      else return false;
    }
    public Result tieBreakStraight(PokerHand hand){
      return tieBreakHighCard(hand);
    }
  
    private boolean isThreeAKind(){
       return (cardValues[0] == cardValues[1] &&
         cardValues[1] == cardValues[2]) ||
        (cardValues[1] == cardValues[2] &&
         cardValues[2] == cardValues[3]) ||
        (cardValues[2] == cardValues[3] &&
         cardValues[3] == cardValues[4]);
    }
    public Result tieBreakThreeAKind(PokerHand hand){
      //return tieBreakFullHouse(hand);
      
       int val1 = 0;
      int val2 = 0;
      
      int[] cv = hand.getCardValues();
      
      val1 = cardValues[2];
      val2 = cv[2];
      
      if(val1 > val2) return Result.WIN;
      if(val1 < val2) return Result.LOSS;
      /*
      if(cardValues[0] == cardValues[1] &&
         cardValues[1] == cardValues[2] &&
         cardValues[3] == cardValues[4]) val1 = cardValues[3];
      else val1 = cardValues[0];
      
       if(cv[0] == cv[1] &&
         cv[1] == cv[2] &&
         cv[3] == cv[4]) val2 = cv[3];
      else val2 = cv[0];
      
       if(val1 > val2) return Result.WIN;
      if(val1 < val2) return Result.LOSS;
      */
      return tieBreakHighCard(hand);
      
    }
  
    private boolean isTwoPair(){
      return  (cardValues[0] == cardValues[1] &&
         cardValues[2] == cardValues[3]) ||
        (cardValues[0] == cardValues[1] &&
         cardValues[3] == cardValues[4]) ||
        (cardValues[1] == cardValues[2] &&
         cardValues[3] == cardValues[4]);
    }
    public Result tieBreakTwoPair(PokerHand hand){
      int val1 = 0;
      int val2 = 0;
      int val3 = 0;
      int val4 = 0;
      
      int[] cv = hand.getCardValues();
      
      if(cardValues[0] == cardValues[1] &&
         cardValues[2] == cardValues[3]){val1=cardValues[3];
                                         val3 = cardValues[1];}
      
       if(cardValues[0] == cardValues[1] &&
         cardValues[3] == cardValues[4]){val1=cardValues[3];
                                         val3 = cardValues[1];}
      
       if(cardValues[1] == cardValues[2] &&
         cardValues[3] == cardValues[4]){val1=cardValues[3];
                                         val3 = cardValues[1];}
   
       if(cv[0] == cv[1] &&
         cv[2] == cv[3]){val2=cv[3];
                                         val4 = cv[1];}
      
       if(cv[0] == cv[1] &&
         cv[3] == cv[4]){val2=cv[3];
                                         val4 = cv[1];}
      
       if(cv[1] == cv[2] &&
         cv[3] == cv[4]){val2=cv[3];
                                         val4 = cv[1];}
      //val1 = cardValues[2];
      //val2 = cv[2];
      
      if(val1 > val2) return Result.WIN;
      if(val1 < val2) return Result.LOSS;
      if(val3 > val4) return Result.WIN;
      if(val3 < val4) return Result.LOSS;
      return tieBreakHighCard(hand);
    }
  
    private boolean isOnePair(){
      return cardValues[0] == cardValues[1] ||
            cardValues[1] == cardValues[2] ||
            cardValues[2] == cardValues[3] ||
        cardValues[3] == cardValues[4];
    }
    public Result tieBreakOnePair(PokerHand hand){
      
      int val1 = 0;
      int val2 = 0;
      
      int[] cv = hand.getCardValues();
      
      //val1 = cardValues[2];
      //val2 = cv[2];
      
      if(cardValues[0] == cardValues[1]) val1=cardValues[0];
      if(cardValues[1] == cardValues[2]) val1=cardValues[1];
      if(cardValues[2] == cardValues[3]) val1=cardValues[2];
      if(cardValues[3] == cardValues[4]) val1=cardValues[3];
 
      if(cv[0] == cv[1]) val2=cv[0];
      if(cv[1] == cv[2]) val2=cv[1];
      if(cv[2] == cv[3]) val2=cv[2];
      if(cv[3] == cv[4]) val2=cv[3];
      
      if(val1 > val2) return Result.WIN;
      if(val1 < val2) return Result.LOSS;
      return tieBreakHighCard(hand);
    }

    private boolean isHighCard(){
      return true;
    }
    public Result tieBreakHighCard(PokerHand hand){
      int val1 = 0;
      int val2 = 0;
      int[] cv = hand.getCardValues();
      
      val1 = cardValues[4];
      val2 = cv[4];
      if(val1 > val2) return Result.WIN;
      if(val1 < val2) return Result.LOSS;
      
      val1 = cardValues[3];
      val2 = cv[3];
      if(val1 > val2) return Result.WIN;
      if(val1 < val2) return Result.LOSS;
      
      val1 = cardValues[2];
      val2 = cv[2];
      if(val1 > val2) return Result.WIN;
      if(val1 < val2) return Result.LOSS;
      
      val1 = cardValues[1];
      val2 = cv[1];
      if(val1 > val2) return Result.WIN;
      if(val1 < val2) return Result.LOSS;
      
      val1 = cardValues[0];
      val2 = cv[0];
      if(val1 > val2) return Result.WIN;
      if(val1 < val2) return Result.LOSS;
      
      return Result.TIE;
    }
  
    PokerHand(String hand)
    {
      this.cards = hand.split(" ");
      
      Comparator<String> cardComparator = new Comparator<String>(){
        @Override
        public int compare(String s1,String s2){
         
          return PokerHand.cardToInt(s1) - PokerHand.cardToInt(s2);
        }
      };
      
      Arrays.sort(this.cards,cardComparator);
      cardValues = new int[5];
      for(int i=0;i<5;++i) cardValues[i] = PokerHand.cardToInt(cards[i]);
      
      if(isStraightFlush()) handStatus = STRAIGHT_FLUSH;
      else if(isFourAKind()) handStatus = FOUR_A_KIND;
           else if(isFullHouse()) handStatus = FULL_HOUSE;
                else if(isFlush()) handStatus = FLUSH;
                else if( isStraight()) handStatus = STRAIGHT;
                     else if(isThreeAKind()) handStatus = THREE_A_KIND;
                          else if(isTwoPair()) handStatus = TWO_PAIR;
                               else if(isOnePair()) handStatus = ONE_PAIR;
                                    else if(isHighCard()) handStatus = HIGH_CARD;
    }

    public Result compareWith(PokerHand hand) {  
        if(handStatus < hand.getHandStatus()) return Result.WIN;
        if(handStatus > hand.getHandStatus()) return Result.LOSS;
        
        if(handStatus == STRAIGHT_FLUSH)
          return tieBreakStraightFlush(hand);
      
        if(handStatus == FOUR_A_KIND)
          return tieBreakFourAKind(hand);
      
        if(handStatus == FULL_HOUSE)
          return tieBreakFullHouse(hand);
      
        if(handStatus == FLUSH)
          return tieBreakFlush(hand);
      
        if(handStatus == STRAIGHT)
          return tieBreakStraight(hand);
      
        if(handStatus == THREE_A_KIND)
          return tieBreakThreeAKind(hand);
      
        if(handStatus == TWO_PAIR)
          return tieBreakTwoPair(hand);
      
        if(handStatus == ONE_PAIR)
          return tieBreakOnePair(hand);
        
        return tieBreakHighCard(hand);
    }
}
