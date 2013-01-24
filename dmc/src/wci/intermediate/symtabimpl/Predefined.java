package wci.intermediate.symtabimpl;

import java.util.ArrayList;

import wci.intermediate.*;
import wci.intermediate.symtabimpl.*;

import static wci.intermediate.symtabimpl.DefinitionImpl.*;
import static wci.intermediate.symtabimpl.SymTabKeyImpl.*;
import static wci.intermediate.symtabimpl.RoutineCodeImpl.*;
import static wci.intermediate.typeimpl.TypeFormImpl.*;
import static wci.intermediate.typeimpl.TypeKeyImpl.*;

/**
 * <h1>Predefined</h1>
 *
 * <p>Enter the predefined Pascal types, identifiers, and constants
 * into the symbol table.</p>
 *
 * <p>Copyright (c) 2008 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public class Predefined
{
    // Predefined types.
    public static TypeSpec integerType;
    public static TypeSpec realType;
    public static TypeSpec stringType;
    public static TypeSpec intListType;
    public static TypeSpec realListType;
    public static TypeSpec stringListType;
    public static TypeSpec funcParamType;
    public static TypeSpec funcType;
    public static TypeSpec funcCallType;
    public static TypeSpec booleanType;
    public static TypeSpec urlType; //mks
    public static TypeSpec charType;
    public static TypeSpec undefinedType;
    public static TypeSpec relType;

    // Predefined identifiers.
    public static SymTabEntry funcId;
    public static SymTabEntry funcCallId;
    public static SymTabEntry funcParamId;
    public static SymTabEntry relId;
    public static SymTabEntry integerId;
    public static SymTabEntry realId;
    public static SymTabEntry stringId;
    public static SymTabEntry intListId;
    public static SymTabEntry realListId;
    public static SymTabEntry stringListId;
    public static SymTabEntry booleanId;
    public static SymTabEntry urlId; //mks
    public static SymTabEntry charId;
    public static SymTabEntry falseId;
    public static SymTabEntry trueId;
    public static SymTabEntry readId;
    public static SymTabEntry readlnId;
    public static SymTabEntry writeId;
    public static SymTabEntry writelnId;
    public static SymTabEntry absId;
    public static SymTabEntry arctanId;
    public static SymTabEntry chrId;
    public static SymTabEntry cosId;
    public static SymTabEntry eofId;
    public static SymTabEntry eolnId;
    public static SymTabEntry expId;
    public static SymTabEntry lnId;
    public static SymTabEntry oddId;
    public static SymTabEntry ordId;
    public static SymTabEntry predId;
    public static SymTabEntry roundId;
    public static SymTabEntry sinId;
    public static SymTabEntry sqrId;
    public static SymTabEntry sqrtId;
    public static SymTabEntry succId;
    public static SymTabEntry truncId;
    public static SymTabEntry displayId; // mks

    /**
     * Initialize a symbol table stack with predefined identifiers.
     * @param symTab the symbol table stack to initialize.
     */
    public static void initialize(SymTabStack symTabStack)
    {
        initializeTypes(symTabStack);
        initializeConstants(symTabStack);
        initializeStandardRoutines(symTabStack);
    }

    /**
     * Initialize the predefined types.
     * @param symTabStack the symbol table stack to initialize.
     */
    public static void initializeTypes(SymTabStack symTabStack)
    {
    	 // Type Relational Op.
        relId = symTabStack.enterLocal("Relational Op"); 
        relType = TypeFactory.createType(SCALAR);
        relType.setIdentifier(relId);
        relId.setDefinition(DefinitionImpl.TYPE);
        relId.setTypeSpec(relType);
        
   	 // Type Relational Op.
        funcParamId = symTabStack.enterLocal("Function Parameters"); 
        funcParamType = TypeFactory.createType(SCALAR);
        funcParamType.setIdentifier(funcParamId);
        funcParamId.setDefinition(DefinitionImpl.TYPE);
        funcParamId.setTypeSpec(funcParamType);
        
        funcId = symTabStack.enterLocal("Function"); 
        funcType = TypeFactory.createType(SCALAR);
        funcType.setIdentifier(funcId);
        funcId.setDefinition(DefinitionImpl.TYPE);
        funcId.setTypeSpec(funcType);
        
        funcCallId = symTabStack.enterLocal("Function Call"); 
        funcCallType = TypeFactory.createType(SCALAR);
        funcCallType.setIdentifier(funcCallId);
        funcCallId.setDefinition(DefinitionImpl.TYPE);
        funcCallId.setTypeSpec(funcCallType);
    	
        // Type integer.
        integerId = symTabStack.enterLocal("Integer"); //mks - changed "integer" to "Integer"
        integerType = TypeFactory.createType(SCALAR);
        integerType.setIdentifier(integerId);
        integerId.setDefinition(DefinitionImpl.TYPE);
        integerId.setTypeSpec(integerType);

        // Type real.
        realId = symTabStack.enterLocal("Real"); // mks - changed "real" to "Real"
        realType = TypeFactory.createType(SCALAR);
        realType.setIdentifier(realId);
        realId.setDefinition(DefinitionImpl.TYPE);
        realId.setTypeSpec(realType);
        
        // Type string.
        stringId = symTabStack.enterLocal("String"); // 
        stringType = TypeFactory.createType(ENUMERATION);
        stringType.setIdentifier(stringId);
        stringId.setDefinition(DefinitionImpl.TYPE);
        stringId.setTypeSpec(stringType);
       
        // Type list.
        intListId = symTabStack.enterLocal("List[Integer]"); // 
        intListType = TypeFactory.createType(ENUMERATION);
        intListType.setIdentifier(intListId);
        intListId.setDefinition(DefinitionImpl.TYPE);
        intListId.setTypeSpec(intListType);
        
        // Type real list.
        realListId = symTabStack.enterLocal("List[Real]"); // 
        realListType = TypeFactory.createType(ENUMERATION);
        realListType.setIdentifier(realListId);
        realListId.setDefinition(DefinitionImpl.TYPE);
        realListId.setTypeSpec(realListType);

        // Type String list.
        stringListId = symTabStack.enterLocal("List[String]"); // 
        stringListType = TypeFactory.createType(ENUMERATION);
        stringListType.setIdentifier(stringListId);
        stringListId.setDefinition(DefinitionImpl.TYPE);
        stringListId.setTypeSpec(stringListType);
        // Type boolean.
        booleanId = symTabStack.enterLocal("Boolean"); //mks - changed "boolean" to "Boolean"
        booleanType = TypeFactory.createType(ENUMERATION);
        booleanType.setIdentifier(booleanId);
        booleanId.setDefinition(DefinitionImpl.TYPE);
        booleanId.setTypeSpec(booleanType);

        // Type url.
        urlId = symTabStack.enterLocal("Url");
        urlType = TypeFactory.createType(ENUMERATION);
        urlType .setIdentifier(urlId);
        urlId.setDefinition(DefinitionImpl.TYPE);
        urlId.setTypeSpec(urlType);
        
        
        
        // Type char.
        charId = symTabStack.enterLocal("char");
        charType = TypeFactory.createType(SCALAR);
        charType.setIdentifier(charId);
        charId.setDefinition(DefinitionImpl.TYPE);
        charId.setTypeSpec(charType);

        // Undefined type.
        undefinedType = TypeFactory.createType(SCALAR);
    }

    /**
     * Initialize the predefined constant.
     * @param symTabStack the symbol table stack to initialize.
     */
    private static void initializeConstants(SymTabStack symTabStack)
    {
        // Boolean enumeration constant false.
        falseId = symTabStack.enterLocal("False"); // mks - changed "false" to "False"
        falseId.setDefinition(DefinitionImpl.ENUMERATION_CONSTANT);
        falseId.setTypeSpec(booleanType);
        falseId.setAttribute(CONSTANT_VALUE, new Integer(0));

        // Boolean enumeration constant true.
        trueId = symTabStack.enterLocal("True"); // mks - changed "true" to "True"
        trueId.setDefinition(DefinitionImpl.ENUMERATION_CONSTANT);
        trueId.setTypeSpec(booleanType);
        trueId.setAttribute(CONSTANT_VALUE, new Integer(1));

        // Add false and true to the boolean enumeration type.
        ArrayList<SymTabEntry> constants = new ArrayList<SymTabEntry>();
        constants.add(falseId);
        constants.add(trueId);
        booleanType.setAttribute(ENUMERATION_CONSTANTS, constants);
    }

    /**
     * Initialize the standard procedures and functions.
     * @param symTabStack the symbol table stack to initialize.
     */
    private static void initializeStandardRoutines(SymTabStack symTabStack)
    {
        readId    = enterStandard(symTabStack, PROCEDURE, "read",    READ);
        readlnId  = enterStandard(symTabStack, PROCEDURE, "readln",  READLN);
        writeId   = enterStandard(symTabStack, PROCEDURE, "write",   WRITE);
        writelnId = enterStandard(symTabStack, PROCEDURE, "writeln", WRITELN);

        absId    = enterStandard(symTabStack, FUNCTION, "abs",    ABS);
        arctanId = enterStandard(symTabStack, FUNCTION, "arctan", ARCTAN);
        chrId    = enterStandard(symTabStack, FUNCTION, "chr",    CHR);
        cosId    = enterStandard(symTabStack, FUNCTION, "cos",    COS);
        eofId    = enterStandard(symTabStack, FUNCTION, "eof",    EOF);
        eolnId   = enterStandard(symTabStack, FUNCTION, "eoln",   EOLN);
        expId    = enterStandard(symTabStack, FUNCTION, "exp",    EXP);
        lnId     = enterStandard(symTabStack, FUNCTION, "ln",     LN);
        oddId    = enterStandard(symTabStack, FUNCTION, "odd",    ODD);
        ordId    = enterStandard(symTabStack, FUNCTION, "ord",    ORD);
        predId   = enterStandard(symTabStack, FUNCTION, "pred",   PRED);
        roundId  = enterStandard(symTabStack, FUNCTION, "round",  ROUND);
        sinId    = enterStandard(symTabStack, FUNCTION, "sin",    SIN);
        sqrId    = enterStandard(symTabStack, FUNCTION, "sqr",    SQR);
        sqrtId   = enterStandard(symTabStack, FUNCTION, "sqrt",   SQRT);
        succId   = enterStandard(symTabStack, FUNCTION, "succ",   SUCC);
        truncId  = enterStandard(symTabStack, FUNCTION, "trunc",  TRUNC);
        
        displayId = enterStandard(symTabStack, FUNCTION, "Display", DISPLAY);
    }

    /**
     * Enter a standard procedure or function into the symbol table stack.
     * @param symTabStack the symbol table stack to initialize.
     * @param defn either PROCEDURE or FUNCTION.
     * @param name the procedure or function name.
     */
    private static SymTabEntry enterStandard(SymTabStack symTabStack,
                                             Definition defn, String name,
                                             RoutineCode routineCode)
    {
        SymTabEntry procId = symTabStack.enterLocal(name);
        procId.setDefinition(defn);
        procId.setAttribute(ROUTINE_CODE, routineCode);

        return procId;
    }
}
