/* Generated By:JJTree: Do not edit this line. ASTIF.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=wci.intermediate.icodeimpl.ICodeNodeImpl,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package wci.frontend;

public
class ASTIF extends SimpleNode {
  public ASTIF(int id) {
    super(id);
  }

  public ASTIF(Language p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(LanguageVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=7edad38c25c6aa9fac43fd6e42ffe55d (do not edit this line) */