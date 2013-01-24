package wci.intermediate;
/*
import wci.frontend.ASTadd;
import wci.frontend.ASTifStatement;
import wci.frontend.ASTsubtract;
import wci.frontend.ASTmultiply;
import wci.frontend.ASTdivide;
import wci.frontend.ASTassignmentStatement;
import wci.frontend.ASTcompoundStatement;
import wci.frontend.ASTintegerConstant;
import wci.frontend.ASTrealConstant;
*/
import wci.frontend.*;
import wci.frontend.ASTProgram;
import wci.frontend.ASTVariable;
import wci.frontend.LanguageVisitor;
import wci.frontend.SimpleNode;


/**
 * TODO: Update parameter names to match the various ASTx classes generated
 * by JJTREE and JAVACC.  Each method has the same body.
 * @author Michael
 *
 */

public class LanguageVisitorAdapter implements LanguageVisitor
{
    public Object visit(SimpleNode node, Object data)
    {
        return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTProgram node, Object data)
    {
    	return node.childrenAccept(this, data);
    }

    public Object visit(ASTStatementList node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTVariable node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTListIndex node, Object data)
    {
        return node.childrenAccept(this, data);
    }
    public Object visit(ASTListSize node, Object data)
    {
        return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTlistItemType node, Object data)
    {
        return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTRelationalOp node, Object data)
    {
        return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTIF node, Object data)
    {
        return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTELSE node, Object data)
    {
        return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTELSEIF node, Object data)
    {
        return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTFor node, Object data)
    {
        return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTRelOp node, Object data)
    {
        return node.childrenAccept(this, data);
    }
    
    

    /* rename! */
    public Object visit(ASTAdd node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTAssignmentStatement node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    public Object visit(ASTFunc node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    public Object visit(ASTFuncParams node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    public Object visit(ASTFuncReturnType node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    public Object visit(ASTfuncId node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    public Object visit(ASTparams node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    public Object visit(ASTTypeSpec node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTFuncCall node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    public Object visit(ASTFuncVar node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    public Object visit(ASTdisplay node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTDivOp node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTintegerConstant node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTstringConstant node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    
    
    public Object visit(ASTLITERAL node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTMultOp node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
   
    public Object visit(ASTrealConstant node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    
    public Object visit(ASTSubtract node, Object data)
    {
    	return node.childrenAccept(this, data);
    }
    
    
}
