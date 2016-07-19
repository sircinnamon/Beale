import java.util.LinkedList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
public class Beale
{
	//dailyprogrammer challenge 274 easy
	//Beale Cipher - List of numbers corresponding to words in declaration of independence
	public static void main(String[] args)
	{
		if(args.length != 2){System.exit(0);}
		String doiFile = args[1];
		String inputFile = args[0];
		LinkedList<String> doi = new LinkedList<String>();
		LinkedList<String> ciphertext = new LinkedList<String>();
		try{doi = readWords(doiFile);}catch(IOException e){e.printStackTrace();}
		try{ciphertext = readWords(inputFile);}catch(IOException e){e.printStackTrace();}
		System.out.println(decode(ciphertext,doi));
	}

	public static LinkedList<String> readWords(String file) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		LinkedList<String> list = new LinkedList<String>();
		String line = br.readLine();
		String[] words;
		while(line != null)
		{
			words = line.split(" ");
			for(String w : words){list.add(w);}
			line = br.readLine();
		}
		return list;
	}

	public static String decode(LinkedList<String> ciphertext, LinkedList<String> doi)
	{
		String plaintext = "";
		int x;
		doi.addFirst(""); //Offset for 1 indexing
		for(String c : ciphertext)
		{
			x = (Integer.parseInt(c.replaceAll("[^0-9]|,","")) % doi.size()-1);
			plaintext = plaintext + (""+doi.get(x).charAt(0)).toLowerCase();
			System.out.println(c.replaceAll(" |,","") + " -> " + doi.get(x));
		}
		return plaintext;
	}
}