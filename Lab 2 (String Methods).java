import java.util.Arrays;

public class Lab2 {
    public static void main(String[] args) {
        String text = "We realizes that while our workers were thriving, the surrounding villages were still suffering. It became our goal to uplift their quality of life as well. I remember seeing a family of 4 on a motorbike in the heavy Bombay rain — I knew I wanted to do more for these families who were risking their lives for lack of an alternative. The alternative mentioned here is the Tata Nano, which soon after came as the world’s cheapest car on retail at a starting price of only Rs 1 lakh. These were the words of Ratan Tata in a recent post by Humans of Bombay which formed the basis of his decision to come up with a car like Tata Nano.";

 // charAt()
char firstChar = text.charAt(0);
System.out.println("\nFirst character: " + firstChar);

// compareTo()
String otherText = "We realize that while our workers were thriving, the surrounding villages were still suffering.";
int comparison = text.compareTo(otherText);
System.out.println("\nComparison: " + comparison);

// concat()
String concatText = text.concat(" This is additional text.");
System.out.println("\nConcatenated text: " + concatText);

// contains()
boolean contains = text.contains("Tata Nano");
System.out.println("\nContains 'Tata Nano': " + contains);

 // endsWith()
boolean endsWith = text.endsWith("Nano.");
System.out.println("\nEnds with 'Nano.': " + endsWith);

 // equals()
boolean equals = text.equals(otherText);
 System.out.println("\nEquals: " + equals);

 // equalsIgnoreCase()
boolean equalsIgnoreCase = text.equalsIgnoreCase(otherText);
System.out.println("\nEquals ignore case: " + equalsIgnoreCase);

 // format()
String formattedText = String.format("The text is: %s", text);
System.out.println("\nFormatted text: " + formattedText);

 // getBytes()
byte[] bytes = text.getBytes();
System.out.println("\nBytes: " + new String(bytes));

// getChars()
char[] chars = new char[10];
text.getChars(0, 10, chars, 0);
System.out.println("\nChars: " + new String(chars));

// indexOf()
int index = text.indexOf("Tata Nano");
System.out.println("\nIndex of 'Tata Nano': " + index);

// intern()
String internedText = text.intern();
System.out.println("\nInterned text: " + internedText);

 // isEmpty()
boolean isEmpty = text.isEmpty();
System.out.println("\nIs empty: " + isEmpty);

// join()
String[] words = text.split(" ");
String joinedText = String.join("-", words);
System.out.println("\nJoined text: " + joinedText);

// lastIndexOf()
int lastIndex = text.lastIndexOf("Tata Nano");
System.out.println("\nLast index of 'Tata Nano': " + lastIndex);

 // length()
int length = text.length();
System.out.println("\nLength: " + length);

// replace()
String replacedText = text.replace("Tata Nano", "Nano car");
System.out.println("\nReplaced text: " + replacedText);

 // replaceAll()
String replacedAllText = text.replaceAll("Tata Nano", "Nano car");
System.out.println("\nReplaced all text: " + replacedAllText);

 // split()
String[] splitText = text.split(" ");
System.out.println("\nSplit text: " + Arrays.toString(splitText));

// startsWith()
boolean startsWith = text.startsWith("We realizes");
System.out.println("\nStarts with 'We realizes': " + startsWith);

// substring()
String substring = text.substring(0, 10);
System.out.println("\nSubstring: " + substring);

 // toCharArray()
char[] charArray = text.toCharArray();
System.out.println("\nChar array: " + new String(charArray));

// toLowerCase()
String lowerCase = text.toLowerCase();
System.out.println("\nLower case: " + lowerCase);

// toUpperCase()
String upperCase = text.toUpperCase();
System.out.println("\nUpper case: " + upperCase);

// trim()
String trimmedText = text.trim();
System.out.println("\nTrimmed text: " + trimmedText);

// valueOf()
String value = String.valueOf(10);
System.out.println("\nValue of 10: " + value);
    }
}
