package webapp;
import java.util.ArrayList;
import java.util.List;

public class Executer {
	static List<Variable> variables;
	
	public static void Execute(ArrayList<Token> tokens) throws Exception {
		ArrayList<Token> namespaceTokens = checkNamespaceStructure(tokens);
		ArrayList<Token> classTokens = null;
		ArrayList<Token> mainMethodTokens = null;
		variables = new ArrayList<Variable>();
		
		
		if (namespaceTokens != null) {
			classTokens = checkClassStructure(namespaceTokens);
			
			mainMethodTokens = checkFunctionStructure(classTokens);
			
			while(!mainMethodTokens.isEmpty()) {
				mainMethodTokens = mainExecution(mainMethodTokens);
			}
			
		}
	}
	
	public static ArrayList<Token> checkNamespaceStructure(ArrayList<Token> tokens) throws Exception {
		int index = 0;
		boolean curlyStructure = false;
		ArrayList<Token> innerTokens = null;
		
		Token token = tokens.get(index);
		
		if (token.getLexeme().equals("namespace")) {
			token = tokens.get(index + 1);
			
			if (token.getTokenType().equals("IDENTIFIER")) {
				if(hasCurlyStructure(tokens)) {
					return new ArrayList<Token>(tokens.subList(3, tokens.size() - 2));
				}
				else {
					throwException("Exception: Syntax error, expected curly brace at line # " + token.getRowNumber());
				}
			}
		}
		else {
			if(token.getLexeme().equals("using")) {
				throwException("Exception: Using statements are not currently supported");
			}
			else {
				throwException("Exception: No namespace was declared at line # " + token.getRowNumber());
			}
		}
		return null;
	}
	
	public static ArrayList<Token> checkClassStructure(ArrayList<Token> tokens) throws Exception{
		Token token = tokens.get(0);
		
		if (token.getTokenType().equals("KEYWORD")) {
			if (token.getLexeme().equals("protected") || token.getLexeme().equals("public") || token.getLexeme().equals("private")) {
				token = tokens.get(1);
				if(token.getLexeme().equals("class")) {
					token = tokens.get(2);
					if (token.getTokenType().equals("IDENTIFIER")) {
						return new ArrayList<Token>(tokens.subList(4, tokens.size() - 1));
					}
				}
				else {
					throwException("Exception: Class must be declared at line # " + token.getRowNumber());
				}
			}
			else {
				throwException("Exception: unexpected class modifier " + token.getLexeme() + " found at line # " + token.getRowNumber());
			}
		}
		else {
			throwException("Exception: unexpected token " + token.getLexeme() + " found at line # " + token.getRowNumber());
		}
		return null;
	}
	
	public static ArrayList<Token> checkFunctionStructure(ArrayList<Token> tokens) throws Exception{
		Token token = tokens.get(0);
		
		if (token.getLexeme().equals("protected") || token.getLexeme().equals("public") || token.getLexeme().equals("private")) {
			token = tokens.get(1);
			if(token.getLexeme().equals("static")) {
				token = tokens.get(2);
				if(token.getLexeme().equals("void")) {
					token = tokens.get(3);
					if(token.getLexeme().toLowerCase().equals("main")) {
						token = tokens.get(4);
						if(token.getLexeme().equals("(")) {
							if(checkFunctionArgs(tokens)) {
								return new ArrayList<Token>(tokens.subList(11, tokens.size() - 1));
							}
							else {
								throwException("Exception: unexpected token " + token.getLexeme() + " found at line # " + token.getRowNumber());
							}
						}
						else {
							throwException("Exception: Missing opening parentheses at line # " + token.getRowNumber());
						}
					}
					else {
						throwException("Exception: Main method return void at line # " + token.getRowNumber());
					}
				}
				else {
					throwException("Exception: Main method return void at line # " + token.getRowNumber());
				}
			}
			else {
				throwException("Exception: Main method must be static at line # " + token.getRowNumber());
			}
		}
		else {
			throwException("Exception: unexpected class modifier " + token.getLexeme() + " found at line # " + token.getRowNumber());
		}
		return null;
	}
	
	public static boolean checkFunctionArgs(ArrayList<Token> tokens) throws Exception{
		Token token = tokens.get(5);
		
		if((token.getLexeme().equals("int"))||(token.getLexeme().equals("string"))||(token.getLexeme().equals("bool"))||(token.getLexeme().equals("double"))){
			token = tokens.get(8);
			if (token.getTokenType().equals("IDENTIFIER")) {
				token = tokens.get(9);
				if (token.getTokenType().equals("RIGHT_PAREN")) {
					return true;
				}
				else {
					throwException("Exception: Variable (" + token.getLexeme() + ") must have a declared name at line # " + token.getRowNumber());
				}
			}
			else {
				throwException("Exception: Variable (" + token.getLexeme() + ") must have a declared name at line # " + token.getRowNumber());
			}
		}
		else {
			throwException("Exception: Variable type (" + token.getLexeme() + ") not supported currently at line # " + token.getRowNumber());
		}
		
		return false;
	}
	
	public static ArrayList<Token> mainExecution(ArrayList<Token> tokens) throws Exception {
		
		Token token = tokens.get(0);
		if((((token.getLexeme().equals("int"))||(token.getLexeme().equals("string"))||(token.getLexeme().equals("bool")) ||(token.getLexeme().equals("double"))) && !(tokens.get(2).getTokenType().equals("ASSIGN_OP")))){
			if (tokens.get(1).getTokenType().equals("IDENTIFIER") && tokens.get(2).getTokenType().equals("SEMICOLON")) {
				switch(tokens.get(0).getLexeme().toLowerCase()) {
					case "int" :
						variables.add(new Variable("INTEGER_LITERAL", "-1", tokens.get(1).getLexeme()));
						return new ArrayList<Token>(tokens.subList(3, tokens.size() - 1));
	
					case "string" :
						variables.add(new Variable("STRING_LITERAL", "-1", tokens.get(1).getLexeme()));
						return new ArrayList<Token>(tokens.subList(3, tokens.size() - 1));
	
					case "bool" :
					 variables.add(new Variable("BOOLEAN", "-1", tokens.get(1).getLexeme()));
					 return new ArrayList<Token>(tokens.subList(3, tokens.size() - 1));
	
					 case "double" :
						 variables.add(new Variable("BOOLEAN", "-1", tokens.get(1).getLexeme()));
						 return new ArrayList<Token>(tokens.subList(3, tokens.size() - 1));
	
					default : 
					   throwException("Exception: Declaration statement invalid syntax at line # " + token.getRowNumber());
					}
				}
			}
			else if(tokens.get(1).getLexeme().equals("=")&&(tokens.get(0).getTokenType().equals("IDENTIFIER"))) {//checks if it is the assignment statement = and the name
				String varName = tokens.get(0).getLexeme();
				boolean varExists = false;
				int index = 0;
				
				
				for (Variable var : variables) {
					if (var.name.equals(varName)) {
						varExists = true;
						variables.remove(index);
						var.value = tokens.get(2).getLexeme();
						variables.add(var);
					}
					index++;
				}
				return new ArrayList<Token>(tokens.subList(4, tokens.size() - 1));
			}
			else if(tokens.get(2).getLexeme().equals("=")&&(tokens.get(1).getTokenType().equals("IDENTIFIER"))) {//checks if it is the assignment statement = and the name
			
				switch(token.getLexeme().toLowerCase()) {
				   case "int" :
				            if(tokens.get(3).getTokenType().equals("INTEGER_LITERAL")){
				            	variables.add(new Variable("INTEGER_LITERAL", tokens.get(3).getLexeme(), tokens.get(1).getLexeme()));
				            	return new ArrayList<Token>(tokens.subList(4, tokens.size() - 1));
				            }
				            else {
				            	throwException("Exception: Invalid string found at line # " + token.getRowNumber());
				            }
				      break; 
				   
				   case "string" :
				      if(tokens.get(3).getTokenType().equals("STRING_LITERAL")){
				    	  variables.add(new Variable("STRING_LITERAL", tokens.get(3).getLexeme(), tokens.get(1).getLexeme()));
				    	  return new ArrayList<Token>(tokens.subList(4, tokens.size() - 1));
				      }
				      break; 
	
			       case "bool" :
			    	  if(tokens.get(3).getLexeme().toLowerCase().equals("true") || tokens.get(3).getLexeme().toLowerCase().equals("false")){
			            	variables.add(new Variable("BOOLEAN", tokens.get(3).getLexeme(), tokens.get(1).getLexeme()));
			            	return new ArrayList<Token>(tokens.subList(4, tokens.size() - 1));
			            }
			            else {
			            	throwException("Exception: Invalid boolean found at line # " + token.getRowNumber());
			            }
			         break; // optional

			         case "double" :
			        	 if(tokens.get(3).getTokenType().equals("REAL_CONSTANT")){
				            	variables.add(new Variable("DOUBLE", tokens.get(3).getLexeme(), tokens.get(1).getLexeme()));
				            	return new ArrayList<Token>(tokens.subList(4, tokens.size() - 1));
				            }
				            else {
				            	throwException("Exception: Invalid double found at line # " + token.getRowNumber());
				            }
			            break; // optional
			  
				   default : 
					   throwException("Exception: Assignment statement invalid syntax at line # " + token.getRowNumber());
					}
				}
		else if (token.getLexeme().equals("while")||token.getLexeme().equals("for")||(token.getLexeme().equals("Console.WriteLine"))) {
			switch(token.getLexeme().toLowerCase()) {
			   case "while" :
			            if((tokens.get(1).getTokenType().equals("OPEN_BRACKET"))&&((tokens.get(5).getTokenType().equals("CLOSE_BRACKET")))){
			            
			            }
			            else {
			            	throwException("Exception: Invalid while found at line # " + token.getRowNumber());
			            }
			      
			   case "for" :
		            if(tokens.get(1).getTokenType().equals("OPEN_BRACKET")&&((tokens.get(15).getTokenType().equals("CLOSE_BRACKET")))){
		            
		            }
		            else {
		            	throwException("Exception: Invalid for found at line # " + token.getRowNumber());
		            }
		     
		      case "Console.WriteLine" :
		            if(tokens.get(1).getTokenType().equals("OPEN_BRACKET")&&(tokens.get(2).getTokenType().equals("\""))&&(tokens.get(3).getTokenType().equals("STRING_LITERAL"))&&(tokens.get(4).getTokenType().equals("OPEN_BRACKET")&&(tokens.get(4).getTokenType().equals("OPEN_BRACKET")))){
		            
		            }
		            else {
		            	throwException("Exception: Invalid Console.writeline found at line # " + token.getRowNumber());
		            }
		     
			   default : 
				   throwException("Exception: Assignment statement invalid syntax at line # " + token.getRowNumber());
				}
		}
		else {
			throwException("An unexpected error occured");
		}
		
		return null;
	}
	
	public static boolean hasCurlyStructure(ArrayList<Token> tokens) {
		int openCount = 0;
		int closedCount = 0;
		
		for (Token token : tokens) {
			if (token.getTokenType().equals("LEFT_CURLY")) {
				openCount++;
			}
			else if (token.getTokenType().equals("RIGHT_CURLY")) {
				closedCount++;
			}
		}
		
		if (openCount == closedCount) {
			return true;
		}
		
		return false;
	}
	
	public static void throwException(String exception) throws Exception{
		throw new Exception(exception);
	}
	
}