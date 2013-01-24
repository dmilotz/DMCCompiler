package wci.intermediate;

import wci.frontend.*;
import wci.intermediate.*;
import wci.intermediate.symtabimpl.*;


/* Changed PclParser refs to Language */
public class TypeSetterVisitor extends LanguageVisitorAdapter
{
    private void setType(SimpleNode node)
    {
        int count = node.jjtGetNumChildren();
        TypeSpec type = Predefined.integerType;
        
        for (int i = 0; (i < count) && (type == Predefined.integerType); ++i) {
            SimpleNode child = (SimpleNode) node.jjtGetChild(i);
            TypeSpec childType = child.getTypeSpec();
            
            if (childType == Predefined.realType) {
                type = Predefined.realType;
            }
        }
        
        node.setTypeSpec(type);
    }
    
    public Object visit(ASTAssignmentStatement node, Object data)
    {
        Object obj = super.visit(node, data);
        setType(node);
        return obj;
    }
    
	
    public Object visit(ASTAdd node, Object data)
    {
        Object obj = super.visit(node, data);
        setType(node);
        return obj;
    }
    public Object visit(ASTSubtract node, Object data)
    {
        Object obj = super.visit(node, data);
        setType(node);
        return obj;
    }
    

    public Object visit(ASTMultOp node, Object data)
    {
        Object obj = super.visit(node, data);
        setType(node);
        return obj;
    }

    public Object visit(ASTDivOp node, Object data)
    {
        Object obj = super.visit(node, data);
        setType(node);
        return obj;
    }
   
    public Object visit(ASTVariable node, Object data)
    {
        return data;
    }
   
    
    public Object visit(ASTintegerConstant node, Object data)
    {
        return data;
    }
    
    public Object visit(ASTrealConstant node, Object data)
    {
        return data;
    }
    
}
