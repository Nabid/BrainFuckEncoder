package com.prome.encoder;

import java.util.Scanner;

/**
 * @author Md. Nabid Imteaj
 * @date december 30, 2014 
 * @version 0.4
 * @see Encoder for BrainF (A-Za-z0-9,.! )
 */

public class Encoder {
	// source string
	public static String src;
	// pointer string
	public static StringBuffer pStr = new StringBuffer( "Aa0" );
	
	/* 
	 * @params source index, pointer index
	 */
	public static String modify( int i, int index ) {
		
		char ch = Encoder.src.charAt(i);
		char pch = Encoder.pStr.charAt(index);
		
		StringBuilder output = new StringBuilder();
		
		// check if larger
		while( pch < ch ) {
			output.append("+");
			pch++;
		}
		// check if smaller
		while( pch > ch ) {
			output.append("-");
			pch--;
		}
		
		// debug
		//System.out.println( "pStr: " + Encoder.pStr );
		
		// set pStr to current character in src
		Encoder.pStr.replace(index, index+1, ""+pch);
		
		return output.toString();
	}
	
	// main thread
	public static void main( String [] args ) {
		
		StringBuffer output = new StringBuffer();
		Scanner sc = new Scanner( System.in );
		int len, i = 0;
		
		// take input
		System.out.println( "Enter string to be encoded: " );
		Encoder.src = sc.nextLine();
		len = Encoder.src.length();
		
		System.out.println( "source length = " + len );
		
		/*----------------------------------------------------------------------
		 * | loop | loop | space(32) | A-Z(65) | a-z(97) | 0-9(48) | newline(10)
		 * --------------------------------------------------------------------- */
		
		// initialize
		output.append( "++++[>++++[>++>++++>++++++>+++>+<<<<<-]<-]++[>+++[>>>>>-<<<<<-]<-]>>>+>+[<]" );
		//output.append( ". >. >. >. >. >. >." ); // test print
		
		while( i < len ) {
			// debug
			//System.out.println( Encoder.src.charAt(i) );
			char ch = Encoder.src.charAt(i); 
			
			if( ch == ' ' ) {
				output.append( ">.<" );
			} else if( ch == '\n' ) {
				output.append(">>>>>.<<<<<");
			} else if( ch == '!' ) {
				output.append(">+.-<");
			} else if( ch == ',' ) {
				output.append("+++[>++++<-]>.<+++[>----<-]");
			} else if( ch == '.' ) {
				output.append("+++[>++++<-]>++.<+++[>----<-]>--<");
			} else if( ch == ':' ) {
				output.append("++++[>++++++<-]>++.<++++[>------<-]>--<");
			} else if( ch == '/' ) {
				output.append("+++[>+++++<-]>.<+++[>-----<-]");
			} else if( ch >= 'a' || ch <= 'z' ) {
				output.append(">>>");
				output.append( Encoder.modify(i, 1) );
				output.append(".<<<");
			} else if( ch >= 'A' || ch <= 'Z' ) {
				output.append(">>");
				output.append( Encoder.modify(i, 0) );
				output.append(".<<");
			} else if( ch >= '0' || ch <= '9' ) {
				output.append(">>>>");
				output.append( Encoder.modify(i, 2) );
				output.append(".<<<<");
			}
			i++;
		}
		
		sc.close();
		// show encoded output
		System.out.println( output );
	}
}
