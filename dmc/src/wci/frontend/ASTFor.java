/* Generated By:JJTree: Do not edit this line. ASTFor.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=wci.intermediate.icodeimpl.ICodeNodeImpl,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package wci.frontend;

public
class ASTFor extends SimpleNode {
  public ASTFor(int id) {
    super(id);
  }

  public ASTFor(Language p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(LanguageVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=f16f433943ef9a84f857f2443fc18867 (do not edit this line) */