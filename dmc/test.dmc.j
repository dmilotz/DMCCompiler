.class public Test
.super java/lang/Object

.field private static counter F
.field private static hello Ljava/lang/String;
.field private static hello2 [Ljava/lang/String;
.field private static helloos [Ljava/lang/String;
.field private static i F
.field private static int I
.field private static int2 I
.field private static int3 I
.field private static int4 I
.field private static intList [I
.field private static l [Ljava/lang/String;
.field private static l2 [Ljava/lang/String;
.field private static r1 F
.field private static r2 F
.field private static r3 F
.field private static r4 F
.field private static real F
.field private static s1 Ljava/lang/String;
.field private static s2 Ljava/lang/String;
.field private static string F
.field private static x F
.field private static y F

.method static exponent(FI)F
    ldc 1.0
    putstatic Test/real F
    ldc 0
    putstatic Test/int I
LabelForLoopStart0:
    getstatic Test/int I
iload_1
    if_icmplt LabelForLoop0
goto ForLoopEnd0
LabelForLoop0:
    getstatic Test/real F
fload_0
    fmul
    putstatic Test/real F
    getstatic Test/int I
    ldc 1
    iadd
    putstatic Test/int I
goto LabelForLoopStart0
ForLoopEnd0:
    getstatic Test/real F
freturn
.limit locals 2
.limit stack 16
.end method

.method static listFunction(I)V
    ldc 0
    putstatic Test/int I
LabelForLoopStart1:
    getstatic Test/int I
iload_0
    if_icmplt LabelForLoop1
goto ForLoopEnd1
LabelForLoop1:
    getstatic Test/hello2 [Ljava/lang/String;
    getstatic Test/int I
    ldc "BYEEEEEE FROM FUNCTION"
    aastore
    getstatic Test/hello2 [Ljava/lang/String;
    getstatic Test/int I
    aaload
    putstatic Test/hello Ljava/lang/String;
getstatic     java/lang/System/out Ljava/io/PrintStream;
    getstatic Test/hello Ljava/lang/String;
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V  
    getstatic Test/int I
    ldc 1
    iadd
    putstatic Test/int I
goto LabelForLoopStart1
ForLoopEnd1:
return
.limit locals 1
.limit stack 16
.end method

.method static listTest(Ljava/lang/String;I)Ljava/lang/String;
    ldc 0
    putstatic Test/int I
LabelForLoopStart2:
    getstatic Test/int I
iload_1
    if_icmplt LabelForLoop2
goto ForLoopEnd2
LabelForLoop2:
    getstatic Test/l [Ljava/lang/String;
    getstatic Test/int I
    ldc "listTest works"
    aastore
    getstatic Test/l [Ljava/lang/String;
    getstatic Test/int I
    aaload
astore_0
    getstatic Test/l2 [Ljava/lang/String;
    getstatic Test/int I
aload_0
    aastore
aload_0
    putstatic Test/s1 Ljava/lang/String;
getstatic     java/lang/System/out Ljava/io/PrintStream;
    getstatic Test/s1 Ljava/lang/String;
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V  
    getstatic Test/int I
    ldc 1
    iadd
    putstatic Test/int I
goto LabelForLoopStart2
ForLoopEnd2:
aload_0
areturn
.limit locals 2
.limit stack 16
.end method

.method public <init>()V

	aload_0
	invokenonvirtual	java/lang/Object/<init>()V
	return

.limit locals 1
.limit stack 1
.end method

.method public static main([Ljava/lang/String;)V

    ldc 25
    anewarray java/lang/String
    putstatic Test/l [Ljava/lang/String;
    ldc 25
    anewarray java/lang/String
    putstatic Test/l2 [Ljava/lang/String;
    ldc 25
    anewarray java/lang/String
    putstatic Test/hello2 [Ljava/lang/String;
    ldc 25
    newarray int
    putstatic Test/intList [I
    ldc 3
    putstatic Test/int3 I
    ldc 4
    putstatic Test/int2 I
    getstatic Test/int2 I
invokestatic Test/listFunction(I)V
    getstatic Test/hello2 [Ljava/lang/String;
    getstatic Test/int3 I
    ldc "hellllooo"
    aastore
    getstatic Test/hello2 [Ljava/lang/String;
    ldc 4
    aaload
    putstatic Test/hello Ljava/lang/String;
    ldc 0
    putstatic Test/int I
LabelForLoopStart3:
    getstatic Test/int I
    ldc 10
    if_icmplt LabelForLoop3
goto ForLoopEnd3
LabelForLoop3:
    getstatic Test/intList [I
    getstatic Test/int I
    ldc 3
    getstatic Test/int I
    iadd
    iastore
    getstatic Test/intList [I
    getstatic Test/int I
    iaload
    putstatic Test/int2 I
getstatic     java/lang/System/out Ljava/io/PrintStream;
    getstatic Test/int2 I
invokevirtual java/io/PrintStream/println(I)V  
    getstatic Test/int I
    ldc 1
    iadd
    putstatic Test/int I
goto LabelForLoopStart3
ForLoopEnd3:
getstatic     java/lang/System/out Ljava/io/PrintStream;
    getstatic Test/int I
invokevirtual java/io/PrintStream/println(I)V  
    ldc 2
    putstatic Test/int4 I
    ldc 3.0
    putstatic Test/r2 F
    getstatic Test/r2 F
    getstatic Test/int4 I
invokestatic Test/exponent(FI)F
    putstatic Test/r4 F
    ldc 3.0
    ldc 2
    i2f
    fmul
    putstatic Test/r2 F
    ldc 2
    i2f
    ldc 4.5
    fmul
    putstatic Test/r3 F
getstatic     java/lang/System/out Ljava/io/PrintStream;
    getstatic Test/r4 F
invokevirtual java/io/PrintStream/println(F)V  
getstatic     java/lang/System/out Ljava/io/PrintStream;
    getstatic Test/r3 F
invokevirtual java/io/PrintStream/println(F)V  
getstatic     java/lang/System/out Ljava/io/PrintStream;
    getstatic Test/r2 F
invokevirtual java/io/PrintStream/println(F)V  
    ldc 20
    putstatic Test/int4 I
    getstatic Test/hello2 [Ljava/lang/String;
    ldc 5
    ldc "This is index 5!"
    aastore
    ldc "bye"
    putstatic Test/s1 Ljava/lang/String;
    getstatic Test/hello Ljava/lang/String;
    getstatic Test/int4 I
invokestatic Test/listTest(Ljava/lang/String;I)Ljava/lang/String;
    putstatic Test/s1 Ljava/lang/String;
getstatic     java/lang/System/out Ljava/io/PrintStream;
    getstatic Test/s1 Ljava/lang/String;
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V  


    return

.limit locals 1
.limit stack  16
.end method
