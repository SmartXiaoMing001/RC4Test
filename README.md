# TextEncryption
```
这是一个可以加解密文件的demo，包括视屏，音频，文本，图片等所有文件的加解密
与DES相比，流密码操作简单，按位异或，并可以根据简单的口令，生成复杂的秘钥进行加密。
This is a demo which can encrypt files & decrypt files,including video,audio,text,picture, etc.
Compared with DES-algorithm，this stream cipher is easier than before. 
We copy a file to other path and xor its byte stream one by one with our stream cipher.
We can also user an easier word to generate a complicated secret key to encrypt files.
```
## 初始测试图片如下：
![image](https://github.com/SmartXiaoMing001/RC4Test/raw/master/pictures/pic.jpg)
## 加密后的图片如下，文件损坏：
![image](https://github.com/SmartXiaoMing001/RC4Test/raw/master/pictures/文件损坏.jpg)
## 重新解密后的图片如下：
![image](https://github.com/SmartXiaoMing001/RC4Test/raw/master/pictures/decFile.jpg)

```
本代码中有两种加密的方式：
第一种是DES加密（com.tman.stream下），由于进行多次轮换和填充，加密的复杂度偏高，加密起来稍微耗时一些
第二种是本人按照自己的思路写的(com.other.stream下)，在拷贝文件时每一位上的字节对应相应的流密码按位异或，解密时也一样
```
### 总结：
```
由于本人还是在校的学生，如果走过路过的同仁觉得对你有帮助，麻烦帮小弟点个赞啦😊😊😊
I just need a star & u could also fork my project,thanks😊😊😊
```
