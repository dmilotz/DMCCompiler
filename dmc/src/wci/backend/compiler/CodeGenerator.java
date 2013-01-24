package wci.backend.compiler;

import java.util.ArrayList;
import java.io.*;

import wci.frontend.*;
import wci.intermediate.*;
import wci.intermediate.symtabimpl.Predefined;
import wci.backend.*;

import static wci.intermediate.icodeimpl.ICodeKeyImpl.ID;
import static wci.intermediate.symtabimpl.SymTabKeyImpl.*;
import static wci.intermediate.symtabimpl.DefinitionImpl.*;

/**
 * <p>The code generator for a compiler back end.</p>
 *
 * <p>Copyright (c) 2008 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public class CodeGenerator extends Backend
{
    private static final int STACK_LIMIT = 16;
    public static Boolean funcsDone = false;    
    static ICode iCode;
    static SymTabStack symTabStack;
    static PrintWriter objectFile;

    /**
     * Process the intermediate code and the symbol table generated by the
     * parser to generate machine-language instructions.
     * @param iCode the intermediate code.
     * @param symTabStack the symbol table stack.
     * @param objectFile the object file path for the generated code.
     * @throws Exception if an error occurred.
     */
    public void process(ICode iCode, SymTabStack symTabStack,
                        String objectFilePath)
        throws Exception
    {
        CodeGenerator.iCode       = iCode;
        CodeGenerator.symTabStack = symTabStack;
        CodeGenerator.objectFile  = new PrintWriter(objectFilePath);

        // Make the program and method names.
        int start = objectFilePath.lastIndexOf("/") + 1;
        String programName = objectFilePath.substring(start);
        int end = programName.indexOf(".");
        if (end > -1) {
            programName = programName.substring(0, end);
        }
        programName = programName.substring(0, 1).toUpperCase() +
                      programName.substring(1);
        String methodName = programName.substring(0, 1).toLowerCase() +
                            programName.substring(1);
        
        SymTabEntry programId = symTabStack.getProgramId();
       // int localsCount = 
         //       (Integer) programId.getAttribute(ROUTINE_LOCALS_COUNT);
        SymTab routineSymTab = 
                (SymTab) programId.getAttribute(ROUTINE_SYMTAB);
        ArrayList<SymTabEntry> locals = routineSymTab.sortedEntries();

        // Generate the program header.
        objectFile.println(".class public " + programName); //mks - programName - fix this!

        objectFile.println(".super java/lang/Object");
        objectFile.println();
        
        /* Generate code for the timer and standard input fields.
        objectFile.println(".field private static _runTimer LRunTimer;");
        objectFile.println(".field private static _standardIn LPascalTextIn;");
        objectFile.println();
        */
        // Generate code for fields.
        for (SymTabEntry id : locals) {
            Definition defn = id.getDefinition();
            
            if (defn == VARIABLE) {
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
                objectFile.println(".field private static " + fieldName + 
                		           " " + typeCode);
                
             }
        }
        for (SymTabEntry id : locals) {
            Definition defn = id.getDefinition();
           
            
            if(defn == FUNCTION){
            	 String fieldName = id.getName();
              
            	 objectFile.println();
                 objectFile.print(".method static " + fieldName +"(");
                 CodeGeneratorVisitorJasmin codeVisitor = new CodeGeneratorVisitorJasmin();
                 Node rootNode = iCode.getRoot();
                 int count = rootNode.jjtGetNumChildren();
            
                 for (int i = 0; i <= count -1; i ++){
                	 SimpleNode node =(SimpleNode) rootNode.jjtGetChild(i);
                	 SymTabEntry name = (SymTabEntry) node.getAttribute(ID);
                	 if(node.getTypeSpec() == Predefined.funcType){
                		 String funcName = name.getName();
             
                	 if(fieldName == funcName){
                		 rootNode.jjtGetChild(i).jjtAccept(codeVisitor, programName);
                	 }
                	 }
                	 //node.jjtAccept(codeVisitor, programName);
                	 
                 }
               
                 //rootNode.jjtAccept(codeVisitor, programName);
                 
            }
         
        }
        funcsDone = true;
        objectFile.println();
        
        // Generate the class constructor.
        objectFile.println(".method public <init>()V");
        objectFile.println();
        objectFile.println("	aload_0");
        objectFile.println("	invokenonvirtual	java/lang/Object/<init>()V");
        objectFile.println("	return");
        objectFile.println();
        objectFile.println(".limit locals 1");
        objectFile.println(".limit stack 1");
        objectFile.println(".end method");
        objectFile.println();
        
        // Generate the main method header.
        objectFile.println(".method public static main([Ljava/lang/String;)V");
        objectFile.println();
        
        /* Generate the main method prologue.
        objectFile.println("    new	 RunTimer");
        objectFile.println("    dup");
        objectFile.println("    invokenonvirtual	RunTimer/<init>()V");
        objectFile.println("    putstatic	" + programName +
        		           "/_runTimer LRunTimer;");
        objectFile.println("    new	 PascalTextIn");
        objectFile.println("    dup");
        objectFile.println("    invokenonvirtual	PascalTextIn/<init>()V");
        objectFile.println("    putstatic	" + programName +
        		           "/_standardIn LPascalTextIn;");
        objectFile.println();
        objectFile.flush();
        */

        // Visit the parse tree nodes to generate code 
        // for the main method's compound statement.
        CodeGeneratorVisitorJasmin codeVisitor = new CodeGeneratorVisitorJasmin();
        Node rootNode = iCode.getRoot();
        rootNode.jjtAccept(codeVisitor, programName);
        objectFile.println();

        /* Generate the main method epilogue.
        objectFile.println("    getstatic	" + programName +
        		           "/_runTimer LRunTimer;");
        objectFile.println("    invokevirtual	RunTimer.printElapsedTime()V");
        */
        objectFile.println();
        objectFile.println("    return");
        objectFile.println();
        objectFile.println(".limit locals " + 1);
        objectFile.println(".limit stack  " + STACK_LIMIT);
        objectFile.println(".end method");
        objectFile.flush();

        CodeGenerator.objectFile.close();
    }
}
