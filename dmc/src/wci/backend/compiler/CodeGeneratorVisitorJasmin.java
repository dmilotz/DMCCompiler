package wci.backend.compiler;

import java.util.ArrayList;
import java.util.HashMap;

import wci.frontend.*;
import wci.intermediate.*;
import wci.intermediate.symtabimpl.Predefined;

import static wci.intermediate.icodeimpl.ICodeKeyImpl.*;

public class CodeGeneratorVisitorJasmin
    extends LanguageVisitorAdapter
    implements LanguageTreeConstants // <-- is this file correct? does it have what it needs?
{
	public int labelCount = 0;
	public HashMap<String,Integer> localMap = new HashMap<String, Integer>();
	public static HashMap<String,String> funcMap = new HashMap<String, String>();
	public static HashMap<String,TypeSpec> funcTypeMap = new HashMap<String, TypeSpec>();
	public static Boolean listAss = false;
	public static int forLoopCount = 0;
	
	
	public Object visit(ASTFunc node, Object data){
		String retString = "";
		if(CodeGenerator.funcsDone == false){
			
			Boolean voidReturn = false;
			  SymTabEntry id = (SymTabEntry) node.getAttribute(ID);
		        String funcName = id.getName();
			//String funcName = (String) node.getAttribute(ID);
		  SimpleNode paramNode   = (SimpleNode) node.jjtGetChild(0);

	      SimpleNode stateNode = (SimpleNode) node.jjtGetChild(2);
	      int paramCount = paramNode.jjtGetNumChildren();
	      //String[] strArr = new String[paramCount];
	      String callAppend = "(";
	      for(int i = 0; i <= paramCount -1; i++){
	    	  SimpleNode paramChildNode   = (SimpleNode) node.jjtGetChild(0).jjtGetChild(i);
	    	  String fieldName = (String) paramChildNode.getAttribute(ID);
	    	  TypeSpec type = paramChildNode.getTypeSpec();
	         // String =  id.getName();
	
	    	  localMap.put(fieldName, i);
	    	  funcTypeMap.put(fieldName,type);
	    	  TypeSpec paramType = paramChildNode.getTypeSpec();
	    	  if(paramType == Predefined.integerType){
	    		  CodeGenerator.objectFile.print("I");
	    	  callAppend = callAppend + "I";
	    	  }
	    	  else if(paramType == Predefined.realType){
	    		  CodeGenerator.objectFile.print("F");
	    		  callAppend = callAppend + "F";
	    	  }
	    	  else if(paramType == Predefined.realListType){
	    		  CodeGenerator.objectFile.print("[F");
	    		  callAppend = callAppend + "[F";
	    	  }
	    	  else if(paramType == Predefined.intListType){
	    		  CodeGenerator.objectFile.print("[I");
	    		  callAppend = callAppend + "[I";
	    	  }
	    	  else if(paramType == Predefined.stringListType){
	    		  CodeGenerator.objectFile.print("[Ljava/lang/String;");
	    		  callAppend = callAppend + "[Ljava/lang/String;";
	    	  }
	    	  else if(paramType == Predefined.stringType){
	    		  CodeGenerator.objectFile.print("Ljava/lang/String;");
	    		  callAppend = callAppend + "Ljava/lang/String;";
	    	  }
	    	  
	      }
	      CodeGenerator.objectFile.print(")");
	      callAppend = callAppend + ")";
	      //Return Type
	      SimpleNode returnNode = (SimpleNode) node.jjtGetChild(1);
	      if(returnNode.jjtGetNumChildren() !=0){
	      SimpleNode returnTypeNode = (SimpleNode) node.jjtGetChild(1).jjtGetChild(0);
    	  TypeSpec retType = returnTypeNode.getTypeSpec();
    	  
    	  if(retType == Predefined.integerType){
    		  CodeGenerator.objectFile.print("I");
    		  callAppend = callAppend + "I";
    		  retString = "i";
    	  }
    	  else if(retType == Predefined.realType){
    		  CodeGenerator.objectFile.print("F");
    		  callAppend = callAppend + "F";
    		  retString = "f";
    	  }
    	  else if(retType == Predefined.realListType){
    		  CodeGenerator.objectFile.print("[F");
    		  callAppend = callAppend + "[F";
    		  retString = "a";
    	  }  else if(retType == Predefined.intListType){
    		  CodeGenerator.objectFile.print("[I");
    		  callAppend = callAppend + "[I";
    		  retString = "a";
    	  }
    	  else if(retType == Predefined.stringListType){
    		  CodeGenerator.objectFile.print("[Ljava/lang/String;");
    		  callAppend = callAppend + "[Ljava/lang/String;";
    		  retString = "a";
    	  }
    	  else if(retType == Predefined.stringType){
    		  CodeGenerator.objectFile.print("Ljava/lang/String;");
    		  callAppend = callAppend + "Ljava/lang/String;";
    		  retString = "a";
    	  }
    	 
    		  
    	  }
	      else{
    		  CodeGenerator.objectFile.print("V");
    		  callAppend = callAppend + "V";
    		  voidReturn = true;
	      }
	      
    	  int nodeCount = node.jjtGetNumChildren();
    	  SimpleNode retNode  = (SimpleNode) node.jjtGetChild(nodeCount-1);
    	 
    	  CodeGenerator.objectFile.println();
    	  stateNode.jjtAccept(this, data);
    	 if(!voidReturn){
    	  retNode.jjtAccept(this, data);
    	 }
    	  CodeGenerator.objectFile.println(retString + "return");
    	
    	  CodeGenerator.objectFile.println(".limit locals " + paramCount);
    	  CodeGenerator.objectFile.println(".limit stack 16");
    	  CodeGenerator.objectFile.println(".end method");
    	
    	  funcMap.put(funcName, callAppend);
		}
		
		
	      return data;
	  }
	   
	public Object visit(ASTFuncCall node, Object data){
		  if(CodeGenerator.funcsDone == true){
		  String programName        = (String) data;
	       SimpleNode funcVarNode   = (SimpleNode) node.jjtGetChild(0);
	       SymTabEntry id = (SymTabEntry) funcVarNode.getAttribute(ID);
	       String fieldName = id.getName();
	       int count = funcVarNode.jjtGetNumChildren();
	       for(int i = 0; i<= count -1; i++){
	    	   SimpleNode paramNode   = (SimpleNode) funcVarNode.jjtGetChild(i);
	    	   paramNode.jjtAccept(this, data);
	       }

	       String append = funcMap.get(fieldName);
	       CodeGenerator.objectFile.println("invokestatic "+ programName+ "/" + fieldName + append) ;
		  }
	    		return data;
	  }
	  

	
	public Object visit(ASTdisplay node, Object data){
			SimpleNode childNode = (SimpleNode) node.jjtGetChild(0);
			   CodeGenerator.objectFile.println("getstatic     java/lang/System/out Ljava/io/PrintStream;");
			   childNode.jjtAccept(this, data);
			   TypeSpec type = childNode.getTypeSpec();
			   String typeCode = "";
			    SymTabEntry id = (SymTabEntry) childNode.getAttribute(ID);
		        String fieldName = "";
		        if(id != null)
		         fieldName = (String) id.getName();
			    if(type == Predefined.stringType || funcTypeMap.get(fieldName) == Predefined.stringType){
		        	typeCode = "Ljava/lang/String;";
		        }
		        else if(type == Predefined.stringListType || funcTypeMap.get(fieldName) == Predefined.stringListType)
		        	typeCode = "[Ljava/lang/String;";
		        else if(type == Predefined.intListType ||  funcTypeMap.get(fieldName) == Predefined.intListType)
		           	typeCode = "[I";
		        else if(type == Predefined.realListType|| funcTypeMap.get(fieldName) == Predefined.realListType)
		        	typeCode = "[F";
		        else if(type == Predefined.integerType || funcTypeMap.get(fieldName) == Predefined.integerType)
		        	typeCode = "I";
		        else if(type == Predefined.realType)
		           	typeCode = "F";
		        
			   CodeGenerator.objectFile.println("invokevirtual java/io/PrintStream/println("+typeCode  +")V  ");
	CodeGenerator.objectFile.flush();
			return data;
		} 
	  
    public Object visit(ASTAssignmentStatement node, Object data)
    {
    	String programName        = (String) data;
        SimpleNode variableNode   = (SimpleNode) node.jjtGetChild(0);
        SimpleNode expressionNode = (SimpleNode) node.jjtGetChild(1);
        SymTabEntry id2 = (SymTabEntry) expressionNode.getAttribute(ID);
        SymTabEntry varId = (SymTabEntry) variableNode.getAttribute(ID);
        TypeSpec type2 = expressionNode.getTypeSpec();
        String name = ""; 
      if((variableNode.getTypeSpec() == Predefined.stringType && 
    		  (expressionNode.getTypeSpec() != Predefined.stringType && expressionNode.getTypeSpec() != Predefined.stringListType))){
    	 name = varId.getName();
    	 System.out.println("Incompatible types at variable: " + name);
      }

      else{ 	
        	if(type2 == Predefined.intListType || type2 == 
        	Predefined.stringListType || type2 == Predefined.realListType){
        	listAss = true;
            
         
		        }
		     if(listAss){
		        SimpleNode listNode   = (SimpleNode) node.jjtGetChild(2);
		        if( (variableNode.getTypeSpec() == Predefined.stringListType  && (expressionNode.getTypeSpec() != Predefined.stringType))){
			      	  name = varId.getName();
			       	 System.out.println("Incompatible types at variable: " + name);}
		        else
		        listNode.jjtAccept(this, data);
		      
		        }
		      
		     else{
			    // Emit code for the expression.
			        expressionNode.jjtAccept(this, data);
			     }
	        TypeSpec expressionType = expressionNode.getTypeSpec();
	     
	        // Get the assignment target type.
	        TypeSpec targetType = node.getTypeSpec();
	        
	        // Removed code that automatically stored all numbers as floats
	        /* Convert an integer value to float if necessary.
	        if ((targetType == Predefined.realType) &&
	            (expressionType == Predefined.integerType))
	        {
	            CodeGenerator.objectFile.println("    i2f");
	            CodeGenerator.objectFile.flush();
	        }
	        */
	
	        SymTabEntry id = (SymTabEntry) variableNode.getAttribute(ID);
	        String fieldName = id.getName();
	        TypeSpec type = id.getTypeSpec();
	        String typeCode;
	        if(type == Predefined.stringType){
	        	typeCode = "Ljava/lang/String;";
	        }
	        else if(type == Predefined.stringListType)
	        	typeCode = "[Ljava/lang/String;";
	        else if(type == Predefined.intListType)
	           	typeCode = "[I";
	        else if(type == Predefined.realListType)
	        	typeCode = "[F";
	        else{
	        typeCode = type == Predefined.integerType ? "I" : "F";
	        }
	        // Emit the appropriate store instruction.
	        if(type == Predefined.funcParamType){
	  
	        	int x = localMap.get(fieldName);
	        	if(funcTypeMap.get(fieldName) == Predefined.integerType)
	        	  CodeGenerator.objectFile.println("istore_" + x);
	        	else if(funcTypeMap.get(fieldName) == Predefined.realType)
	        		CodeGenerator.objectFile.println("fstore_" + x);
	        	else if(funcTypeMap.get(fieldName) == Predefined.stringType)
	        		CodeGenerator.objectFile.println("astore_" + x);
	        	else if(funcTypeMap.get(fieldName) == Predefined.realType)
	        		CodeGenerator.objectFile.println("astore_" + x);
	        	else if(funcTypeMap.get(fieldName) == Predefined.realListType)
	        		CodeGenerator.objectFile.println("astore_" + x);
	        	else if(funcTypeMap.get(fieldName) == Predefined.stringListType)
	        		CodeGenerator.objectFile.println("astore_" + x);
	        	else if(funcTypeMap.get(fieldName) == Predefined.intListType)
	        		CodeGenerator.objectFile.println("astore_" + x);
	        }
	        else if(type != Predefined.stringListType && type != Predefined.realListType && type != Predefined.intListType ||
	        		node.jjtGetNumChildren() == 2 ){
	        CodeGenerator.objectFile.println("    putstatic " + programName +
	        		                         "/" + fieldName + " " + typeCode);
	        CodeGenerator.objectFile.flush();
	        }
      }
        return data;
    }


	
	public Object visit(ASTVariable node, Object data)
    {
    	String programName = (String) data;
        SymTabEntry id = (SymTabEntry) node.getAttribute(ID);
        String fieldName = id.getName();
        TypeSpec type = id.getTypeSpec();
        String typeCode;
        if(type == Predefined.stringType){
        	typeCode = "Ljava/lang/String;";
        }
        else if(type == Predefined.stringListType)
        	typeCode = "[Ljava/lang/String;";
        else if(type == Predefined.intListType)
           	typeCode = "[I";
        else if(type == Predefined.realListType)
        	typeCode = "[F";
        else{
        typeCode = type == Predefined.integerType ? "I" : "F";
        }
        // Emit the appropriate load instruction.
        if(type == Predefined.funcParamType){
        
        	int x = localMap.get(fieldName);
        	if(funcTypeMap.get(fieldName) == Predefined.integerType)
          	  CodeGenerator.objectFile.println("iload_" + x);
          	else if(funcTypeMap.get(fieldName) == Predefined.realType)
          		CodeGenerator.objectFile.println("fload_" + x);
          	else if(funcTypeMap.get(fieldName) == Predefined.stringType)
          		CodeGenerator.objectFile.println("aload_" + x);
          	else if(funcTypeMap.get(fieldName) == Predefined.realListType)
          		CodeGenerator.objectFile.println("aload_" + x);
          	else if(funcTypeMap.get(fieldName) == Predefined.stringListType)
          		CodeGenerator.objectFile.println("aload_" + x);
          	else if(funcTypeMap.get(fieldName) == Predefined.intListType)
          		CodeGenerator.objectFile.println("aload_" + x);
        }
        else{
        CodeGenerator.objectFile.println("    getstatic " + programName +
                "/" + fieldName + " " + typeCode);
        CodeGenerator.objectFile.flush();
        }
        
        if(node.jjtGetNumChildren()==1){
        	node.jjtGetChild(0).jjtAccept(this, data);
        }
        return data;
    }


    public Object visit(ASTintegerConstant node, Object data)
    {
        int value = (Integer) node.getAttribute(VALUE);

        // Emit a load constant instruction.
        CodeGenerator.objectFile.println("    ldc " + value);
        CodeGenerator.objectFile.flush();

        return data;
    }

    
    public Object visit(ASTrealConstant node, Object data)
    {
        float value = (Float) node.getAttribute(VALUE);

        // Emit a load constant instruction.
        CodeGenerator.objectFile.println("    ldc " + value);
        CodeGenerator.objectFile.flush();

        return data;
    }

    public Object visit(ASTstringConstant node, Object data)
    {
        String value = (String) node.getAttribute(VALUE);

        // Emit a load constant instruction.
        CodeGenerator.objectFile.println("    ldc " + value);
        CodeGenerator.objectFile.flush();

        return data;
    }

    public Object visit(ASTListSize node, Object data){
    	SimpleNode childNode = (SimpleNode) node.jjtGetChild(0);
    	SimpleNode parentNode = (SimpleNode) node.jjtGetParent();
    	SimpleNode typeNode = (SimpleNode) parentNode.jjtGetChild(0);
    	int value = (Integer) childNode.getAttribute(VALUE);
      
        
        SymTabEntry id = (SymTabEntry) typeNode.getAttribute(ID);
        TypeSpec type = id.getTypeSpec();
        String typeCode;
        String fieldName="";
        if(id != null)
         fieldName = (String) id.getName();
      	if(type == Predefined.stringListType || funcTypeMap.get(fieldName) == Predefined.stringListType)
        	typeCode = "    anewarray java/lang/String";
        else if(type == Predefined.intListType || funcTypeMap.get(fieldName) == Predefined.intListType)
           	typeCode = "    newarray int";
        else
        	typeCode = "    newarray float";
        
        CodeGenerator.objectFile.println("    ldc " + value);
        CodeGenerator.objectFile.println(typeCode);
        CodeGenerator.objectFile.flush();
        return data;
    }
    
    public Object visit(ASTListIndex node, Object data){
    	TypeSpec varType;

    		SimpleNode addend0Node = (SimpleNode) node.jjtGetChild(0);
    	SimpleNode parentNode = (SimpleNode) node.jjtGetParent();
    	SimpleNode typeNode = (SimpleNode) parentNode.jjtGetChild(2);
    	SimpleNode varNode = (SimpleNode) parentNode.jjtGetChild(0);
    
    	String name = "";
        if(listAss){
        	SymTabEntry listName = (SymTabEntry) typeNode.getAttribute(ID);
        	//name = listName.getName();
        	SimpleNode var2Node = (SimpleNode) parentNode.jjtGetChild(1);
        	if((varNode.getTypeSpec() == Predefined.stringType && var2Node.getTypeSpec() == Predefined.stringListType)||
        			(varNode.getTypeSpec() == Predefined.integerType && var2Node.getTypeSpec() == Predefined.intListType)||
        			(varNode.getTypeSpec() == Predefined.realType && var2Node.getTypeSpec() == Predefined.realListType)){
        	var2Node.jjtAccept(this, data);
        	addend0Node.jjtAccept(this, data);
        	varType = var2Node.getTypeSpec();
        	
        	
        	  if(varType == Predefined.intListType){
        	       CodeGenerator.objectFile.println("    iaload");
        	        CodeGenerator.objectFile.flush();
        	        }
        	        else if(varType == Predefined.realListType){
        	            CodeGenerator.objectFile.println("    faload");
        	             CodeGenerator.objectFile.flush();
        	             }
        	        else {
        	            CodeGenerator.objectFile.println("    aaload");
        	             CodeGenerator.objectFile.flush();
        	             }
        	 // varNode.jjtAccept(this,data);
        	  listAss= false;
        	}
        	else
        		System.out.println("Incompatible types for list: " +name);
        }
        else{	SymTabEntry listName = (SymTabEntry) varNode.getAttribute(ID);
    	name = listName.getName();
         	if((typeNode.getTypeSpec() == Predefined.stringType && varNode.getTypeSpec() == Predefined.stringListType)||
        			(typeNode.getTypeSpec() == Predefined.integerType && varNode.getTypeSpec() == Predefined.intListType)||
        			(typeNode.getTypeSpec() == Predefined.realType && varNode.getTypeSpec() == Predefined.realListType)){
          	varNode.jjtAccept(this, data);
        	addend0Node.jjtAccept(this, data);
        	typeNode.jjtAccept(this, data);
        	varType = varNode.getTypeSpec();
       
        if(varType == Predefined.intListType){
       CodeGenerator.objectFile.println("    iastore");
        CodeGenerator.objectFile.flush();
        }
        else if(varType == Predefined.realListType){
            CodeGenerator.objectFile.println("    fastore");
             CodeGenerator.objectFile.flush();
             }
        else {
            CodeGenerator.objectFile.println("    aastore");
             CodeGenerator.objectFile.flush();
             }
        			}
        			else
        				System.out.println("Incompatibles types for list: " + name);
        }
        
        return data;
    }
    
    public Object visit(ASTAdd node, Object data)
    {
        SimpleNode addend0Node = (SimpleNode) node.jjtGetChild(0);
        SimpleNode addend1Node = (SimpleNode) node.jjtGetChild(1);
        SimpleNode assNode = (SimpleNode) node.jjtGetParent();
        SimpleNode assVarNode = (SimpleNode) assNode.jjtGetChild(0);
        
        TypeSpec assType = assVarNode.getTypeSpec();
        TypeSpec type0 = addend0Node.getTypeSpec();
        TypeSpec type1 = addend1Node.getTypeSpec();
        // Get the addition type.
        String typePrefix = "";
        TypeSpec type = node.getTypeSpec();
        SymTabEntry id = (SymTabEntry) addend0Node.getAttribute(ID);
        String fieldName;
        if(id != null){
         fieldName = (String) id.getName();
        
       // String fieldName = (String) addend0Node.getAttribute(ID);
     	if(funcTypeMap.get(fieldName) == Predefined.integerType)
     		typePrefix = "i";
      	else if(funcTypeMap.get(fieldName) == Predefined.realType)
      		typePrefix = "f";
      	else if(type0 == Predefined.integerType || type1 == Predefined.integerType)
        	typePrefix = "i";
        else
        	typePrefix = "f";
        }
        else{
        if(type0 == Predefined.integerType || type1 == Predefined.integerType)
        	typePrefix = "i";
        else
        	typePrefix = "f";
        }

        // Emit code for the first expression
        // with type conversion if necessary.
        addend0Node.jjtAccept(this, data);


        // Emit code for the second expression
   
     // with type conversion if necessary.
      
        addend1Node.jjtAccept(this, data);


        // Emit the appropriate add instruction.
        CodeGenerator.objectFile.println("    " + typePrefix + "add");
        CodeGenerator.objectFile.flush();

        return data;
    }
    
 //For loop////////////////////////////////////////////////////////////////
    
    public Object visit(ASTFor node, Object data){
    	int loopCount = 0;
    	SimpleNode var1Node = (SimpleNode) node.jjtGetChild(0);
        SimpleNode relOpNode = (SimpleNode) node.jjtGetChild(1);
        SimpleNode relVar1Node = (SimpleNode) node.jjtGetChild(1).jjtGetChild(0);
        SimpleNode relSymNode = (SimpleNode) node.jjtGetChild(1).jjtGetChild(1);
        SimpleNode relVar2Node = (SimpleNode) node.jjtGetChild(1).jjtGetChild(2);
        SimpleNode var2Node = (SimpleNode) node.jjtGetChild(2);
        SimpleNode stateNode = (SimpleNode) node.jjtGetChild(3);
     	var1Node.jjtAccept(this, data);

    	CodeGenerator.objectFile.println("LabelForLoopStart"+forLoopCount+":");
    	relVar1Node.jjtAccept(this, data);
    	relVar2Node.jjtAccept(this, data);
    	relSymNode.jjtAccept(this, data);
    	CodeGenerator.objectFile.println("ForLoop"+forLoopCount+"");
    	CodeGenerator.objectFile.println("goto ForLoopEnd"+forLoopCount+"");
    	
    	CodeGenerator.objectFile.println("LabelForLoop"+forLoopCount+":");
    	stateNode.jjtAccept(this, data);
    	var2Node.jjtAccept(this, data);
    	CodeGenerator.objectFile.println("goto LabelForLoopStart"+forLoopCount+"");
    	CodeGenerator.objectFile.println("ForLoopEnd"+forLoopCount+++":");
    	
    	return data;
    	
    	
    	
    }
    
 //If statement/////////////////////////////////////////////////////////////////////////   
    public Object visit(ASTIF node, Object data){
    	CodeGenerator.objectFile.println();
    	CodeGenerator.objectFile.println("    ; If Statement");
    	CodeGenerator.objectFile.println();
    	ArrayList<SimpleNode> nodeList = new ArrayList<SimpleNode>();
    	ArrayList<Integer> labelList = new ArrayList<Integer>();
    	SimpleNode var1Node = (SimpleNode) node.jjtGetChild(0).jjtGetChild(0); 
    	SimpleNode relNode = (SimpleNode) node.jjtGetChild(0).jjtGetChild(1); 
    	SimpleNode var2Node = (SimpleNode) node.jjtGetChild(0).jjtGetChild(2); 
    	SimpleNode stateNode = (SimpleNode) node.jjtGetChild(1);
     	var1Node.jjtAccept(this, data);
    	var2Node.jjtAccept(this, data);
    	relNode.jjtAccept(this, data);
   	 	CodeGenerator.objectFile.println(labelCount);
   	 	//CodeGenerator.objectFile.println("goto    Label" + ++labelCount  );
        CodeGenerator.objectFile.flush();
        nodeList.add(stateNode);
        labelList.add(labelCount++);
        //labelCount++;
       // stateNode.jjtAccept(this, data);
      	
        //Else nodes
        int count = node.jjtGetNumChildren();
      	if(count > 2){
      		if(count == 3){
      			SimpleNode elseNode = (SimpleNode) node.jjtGetChild(2);
      			int elseCount = elseNode.jjtGetNumChildren();
      			if(elseCount  == 1){
    			SimpleNode elseStateNode = (SimpleNode) elseNode.jjtGetChild(0);
    			CodeGenerator.objectFile.println(" goto   Label" + ++labelCount);
    			//elseStateNode.jjtAccept(this, data);
    			nodeList.add(elseStateNode);
    			labelList.add(labelCount++);
      			}
      			else{
      			 	SimpleNode elseVar1Node = (SimpleNode) elseNode.jjtGetChild(0).jjtGetChild(0); 
	      		 	SimpleNode elseRelNode = (SimpleNode) elseNode.jjtGetChild(0).jjtGetChild(1); 
	      		 	SimpleNode elseVar2Node = (SimpleNode) elseNode.jjtGetChild(0).jjtGetChild(2); 
	      			SimpleNode elseStateNode = (SimpleNode) elseNode.jjtGetChild(1);
	      			elseVar1Node.jjtAccept(this, data);
	      			elseVar2Node.jjtAccept(this, data);
	      			elseRelNode.jjtAccept(this, data);
	      	   	 	CodeGenerator.objectFile.println(labelCount);
	      	    	
	      	      // CodeGenerator.objectFile.println(" goto   Label" + ++labelCount);
	      	       // CodeGenerator.objectFile.flush();
	      	        //elseStateNode.jjtAccept(this, data);
	      	      nodeList.add(elseStateNode);
	    			labelList.add(labelCount++);
	    		
      			}
      		}
      		else {	
      			for ( int i = 2; i <= count -1; i ++){
      					SimpleNode elseNode = (SimpleNode) node.jjtGetChild(i);
      					int elseCount = elseNode.jjtGetNumChildren();
      	      			if(elseCount  == 1){
      	    			SimpleNode elseStateNode = (SimpleNode) elseNode.jjtGetChild(0);
      	    			nodeList.add(elseStateNode);
      	    			labelList.add(labelCount++);
      	    			CodeGenerator.objectFile.println("    goto Label" + --labelCount);
      	    			// CodeGenerator.objectFile.println("    Label" + labelCount++ + ":" );
      		      	    //    CodeGenerator.objectFile.flush();
      		      	   // elseStateNode.jjtAccept(this, data)
      	      			}
      	      			else{
      	      			 	SimpleNode elseVar1Node = (SimpleNode) elseNode.jjtGetChild(0).jjtGetChild(0); 
      		      		 	SimpleNode elseRelNode = (SimpleNode) elseNode.jjtGetChild(0).jjtGetChild(1); 
      		      		 	SimpleNode elseVar2Node = (SimpleNode) elseNode.jjtGetChild(0).jjtGetChild(2); 
      		      			SimpleNode elseStateNode = (SimpleNode) elseNode.jjtGetChild(1);
      		      			elseVar1Node.jjtAccept(this, data);
      		      			elseVar2Node.jjtAccept(this, data);
      		      			elseRelNode.jjtAccept(this, data);
      		      	   	 	CodeGenerator.objectFile.println(labelCount);
      		      	    	
      		      	     //   CodeGenerator.objectFile.println(" goto   Label" + ++labelCount  );
      		      	        //CodeGenerator.objectFile.flush();
      		      	        //elseStateNode.jjtAccept(this, data);
      		      	    nodeList.add(elseStateNode);
      	    			labelList.add(labelCount);
      	    			labelCount++;
      		      	
      	      			}
      			}
      		}
      	}
      	
      	for(int i = 0; i <= nodeList.size() -1; i++){
			 CodeGenerator.objectFile.println("    Label" + labelList.get(i) + ":" );
    	        CodeGenerator.objectFile.flush();
    	        nodeList.get(i).jjtAccept(this, data);
    	    	CodeGenerator.objectFile.println("    goto End");
	      	        CodeGenerator.objectFile.flush();
    	        if(i == nodeList.size() -1){
    	        	//CodeGenerator.objectFile.println("    goto End");
		    			 CodeGenerator.objectFile.println("    End:" );
		    			 	CodeGenerator.objectFile.println();
			      	        CodeGenerator.objectFile.flush();
    	        }
      	}
      	
      	return data;
    }
    
    public Object visit(ASTRelationalOp node, Object data){
    	String value = (String) node.getAttribute(VALUE);
    	//int labelCount = (Integer) data;
    	String type;
    	if(value.equals(">"))
    		type = "if_icmpgt";
    	else if(value.equals("<"))
    		type = "if_icmplt";
    	else if(value.equals("<="))
    		type = "if_icmple";
    	else if(value.equals(">="))
    		type = "if_icmpge";
    	else if(value.equals("=="))
    		type = "if_icmpeq";
    	else
    		type = "if_icmpne";
 
        // Emit the appropriate compare instruction.
        CodeGenerator.objectFile.print("    " + type + " Label");
        CodeGenerator.objectFile.flush();
       // data = labelCount;
        return data;
    	
    }

    public Object visit(ASTSubtract node, Object data)
    {             SimpleNode addend0Node = (SimpleNode) node.jjtGetChild(0);
    SimpleNode addend1Node = (SimpleNode) node.jjtGetChild(1);
    SimpleNode assNode = (SimpleNode) node.jjtGetParent();
    SimpleNode assVarNode = (SimpleNode) assNode.jjtGetChild(0);
    
    TypeSpec assType = assVarNode.getTypeSpec();
    TypeSpec type0 = addend0Node.getTypeSpec();
    TypeSpec type1 = addend1Node.getTypeSpec();
    // Get the addition type.
    String typePrefix = "";
    TypeSpec type = node.getTypeSpec();
    SymTabEntry id = (SymTabEntry) addend0Node.getAttribute(ID);
    String fieldName;
    if(id != null){
     fieldName = (String) id.getName();
    
   // String fieldName = (String) addend0Node.getAttribute(ID);
 	if(funcTypeMap.get(fieldName) == Predefined.integerType)
 		typePrefix = "i";
  	else if(funcTypeMap.get(fieldName) == Predefined.realType)
  		typePrefix = "f";
  	else if(type0 == Predefined.integerType || type1 == Predefined.integerType)
    	typePrefix = "i";
    else
    	typePrefix = "f";
    }
    else{
    if(type0 == Predefined.integerType || type1 == Predefined.integerType)
    	typePrefix = "i";
    else
    	typePrefix = "f";
    }

    // Emit code for the first expression
    // with type conversion if necessary.
    addend0Node.jjtAccept(this, data);


    // Emit code for the second expression

 // with type conversion if necessary.
  
    addend1Node.jjtAccept(this, data);


        // Emit the appropriate add instruction.
        CodeGenerator.objectFile.println("    " + typePrefix + "sub");
        CodeGenerator.objectFile.flush();

        return data;
    }

    public Object visit(ASTMultOp node, Object data)
    {        SimpleNode addend0Node = (SimpleNode) node.jjtGetChild(0);
    SimpleNode addend1Node = (SimpleNode) node.jjtGetChild(1);
    SimpleNode assNode = (SimpleNode) node.jjtGetParent();
    SimpleNode assVarNode = (SimpleNode) assNode.jjtGetChild(0);
    
    TypeSpec assType = assVarNode.getTypeSpec();
    TypeSpec type0 = addend0Node.getTypeSpec();
    TypeSpec type1 = addend1Node.getTypeSpec();
    // Get the addition type.
    String typePrefix = "";
    TypeSpec type = node.getTypeSpec();
    SymTabEntry id = (SymTabEntry) addend0Node.getAttribute(ID);
    String fieldName;
    if(id != null){
     fieldName = (String) id.getName();
    
   // String fieldName = (String) addend0Node.getAttribute(ID);
 	if(funcTypeMap.get(fieldName) == Predefined.integerType)
 		typePrefix = "i";
  	else if(funcTypeMap.get(fieldName) == Predefined.realType)
  		typePrefix = "f";
  	else if(type0 == Predefined.integerType || type1 == Predefined.integerType)
    	typePrefix = "i";
    else
    	typePrefix = "f";
    }
    else{
    if(type0 == Predefined.integerType || type1 == Predefined.integerType)
    	typePrefix = "i";
    else
    	typePrefix = "f";
    }

    // Emit code for the first expression
    // with type conversion if necessary.
    addend0Node.jjtAccept(this, data);


    // Emit code for the second expression

 // with type conversion if necessary.
  
    addend1Node.jjtAccept(this, data);



        // Emit the appropriate add instruction.
        CodeGenerator.objectFile.println("    " + typePrefix + "mul");
        CodeGenerator.objectFile.flush();

        return data;
    }

    public Object visit(ASTDivOp node, Object data)
    {
        SimpleNode addend0Node = (SimpleNode) node.jjtGetChild(0);
        SimpleNode addend1Node = (SimpleNode) node.jjtGetChild(1);
        SimpleNode assNode = (SimpleNode) node.jjtGetParent();
        SimpleNode assVarNode = (SimpleNode) assNode.jjtGetChild(0);
        
        TypeSpec assType = assVarNode.getTypeSpec();
        TypeSpec type0 = addend0Node.getTypeSpec();
        TypeSpec type1 = addend1Node.getTypeSpec();
        // Get the addition type.
        String typePrefix = "";
        TypeSpec type = node.getTypeSpec();
        SymTabEntry id = (SymTabEntry) addend0Node.getAttribute(ID);
        String fieldName;
        if(id != null){
         fieldName = (String) id.getName();
        
       // String fieldName = (String) addend0Node.getAttribute(ID);
     	if(funcTypeMap.get(fieldName) == Predefined.integerType)
     		typePrefix = "i";
      	else if(funcTypeMap.get(fieldName) == Predefined.realType)
      		typePrefix = "f";
      	else if(type0 == Predefined.integerType || type1 == Predefined.integerType)
        	typePrefix = "i";
        else
        	typePrefix = "f";
        }
        else{
        if(type0 == Predefined.integerType || type1 == Predefined.integerType)
        	typePrefix = "i";
        else
        	typePrefix = "f";
        }

        // Emit code for the first expression
        // with type conversion if necessary.
        addend0Node.jjtAccept(this, data);


        // Emit code for the second expression
   
     // with type conversion if necessary.
      
        addend1Node.jjtAccept(this, data);



        // Emit the appropriate add instruction.
        CodeGenerator.objectFile.println("    " + typePrefix + "div");
        CodeGenerator.objectFile.flush();

        return data;
    }
    
    
    
}
