# 常用函数插件

## ScriptEval
该函数中可以使用javascript脚本，避免嵌套各种IF，SWITCH函数的写法。
在模板中A4格式是一个扩展格子（向下扩展），值为[1,2,3,4,5]，加入我们需要让B4格子的值满足以下条件

当A4的值大于4的时候，返回A4 * 2的值，当A4的值小于4且大于1的时候，返回A4减2的绝对值，其他时候，返回A4的A4的2次方。

如果用传统的函数，写法：
```
if(A4 > 4, A4 * 2, if(and(A4 < 4, A4 > 1), abs(A4 - 2), power(A4, 2)))  
```
而如果使用ScriptEval函数写法：
```
ScriptEval('if(a > 4) {return a * b;} else if (a < 4 && a > 1){return Math.abs(a - b);} else {return Math.pow(a, b)}', "a", A4, "b", 2) 
```

## StringReverse
把给定的参数字符串做倒转。

##StringFind	
查找第一个匹配的字符串，支持从头开始查找和从尾部开始查找。

## StringMatch
判断字符串是否符合给定的正则表达式。

##StringFetch
根据正则表达式抓取给定字符串中匹配的部分，以数组形式返回。

##StringPinyin
将中文转换为拼音。

##StringShortPinyin	
将中文装换为拼音首字母。

##StringSwapCase
将字符串中的大小写字母调换大小写。StringSwapCase("abcDEF") 返回ABCdef。

##NumberToEnglish
将数字转换成英文。NumberToEnglish(1234567)将返回One Million, Two Hundred And Thirty-Four Thousand, Five Hundred And Sixty-Seven。

##GetIP
获取当前访问的用户的IP地址。

##ProcessErrorValue
处理函数返回错误值，一般函数发生错误时，会输出#ERROR_NAME之类的，这个函数可以在函数返回值为错误值的时候，输出自定义的值，其他值不变。

##JSONPathFinder
从JSON文本中查找符合条件的结果，JSON支持文本、文件路径以及URL。

##MathFrequency
统计单词出现的次数和频率。MathFrequency([1, 3, 2, 4, 2], 3)返回1，表示数组中3出现了一次，如果给定了第三个参数，则返回0.2，表示3出现的频率为0.2。

##MathVariance
求数组中元素的方差。

##MathStandardDeviation
求数组中元素的标准差。

##MathSumOfSquares
求数组中元素的平方和。

##MathGeometricMean
求数组中元素的几何平均数。

##MathMean
求数组中元素的算术平均数。
