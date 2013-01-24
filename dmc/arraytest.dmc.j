-- Declarations
Real $real;
Integer $int;
List[Integer] $intList;
List[String] $strList;
List[Real] $realList;

--Array Size Declaration
$intList = [25];
$strList = [10];
$realList = [5];

--Other variable declarations
$real = 3.0;
$int = 11;

--Array Index declarations
$strList[8] = "hello world";
$intList[23] = 100;
$intList[$int] = 20;
$realList[3] = 3.0;
$real = 3.0 * 4.0;
$int = 6 / 3;

$real = 3.0 + 4.0;
$int = 6 - 3;




for($int = 0; $int < 10; $int = $int + 1){
$intList[$int] = 3 + $int;
};

--If statements

getstatic     java/lang/System/out Ljava/io/PrintStream;
getstatic Test/int4 I 
invokevirtual java/io/PrintStream/println(I)V  



function fuckedListFunction(Integer $counter):void{
for($int = 0; $int < $counter; $int = $int +1){
$hello2[$int] = "helloOO";};
return ;
};

$int3 = 3;
$int2 = 4;

fuckedListFunction($int2);
