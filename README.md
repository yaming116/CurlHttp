# CurlHttp
Curl library for Android
> libcurl通过URL语法传送数据，支持`FTP ， FTPS ，HTTP ，HTTPS， GOPHER ， TFTP ，SCP， SFTP ，TELNET， DICT ，LDAP， LDAPS ，FILE ，IMAP， SMTP ， POP3， RTSP库和RTMP`协议 。总之 libcurl是一个功能非常强大的C开源网络库[官网](http://curl.haxx.se/libcurl/c/)，[GitHub](https://github.com/curl/curl)

支持`armeabi,armeabi-v7, x86`平台下 的使用， 通过java jni调用，可以完成jni写的native网络请求。
###目前支持
- [x] addHeader
- [x] setBody
- [x] setHttpProxy
- [x] useSystemProxy
- [x] setConnectionTimeoutMillis
- [x] setTimeoutMillis
- [x] setMaxRedirects
- [x] setIpResolveV4
- [x] setIpResolveV6
- [x] setIpResolveWhatever
- [x] addParam
- [x] addMultiPartPostParam
- [x] postUrl
- [x] getUrl

###用法示例

####GET请求
``` java
CurlResult result = CurlHttp.newInstance()
                        .addParam("version", "332")
                        .addParam("type", "subject")
                        .addParam("channel_mark", "豌豆荚")
                        .addParam("attr", "4")
                        .addParam("lang", "zh")
                        .addParam("ftType", "2")
                        .getUrl("http://cdn6.xinmei365.com/cdndata/banner/banner")
                        .execute();
```

###CURL状态码列表

| 状态码 | 对应值| 状态原因 | 解释 |
| :------------ |:---------------:|:---------------|:---------------|
| CURLE_OK|0  | 正常访问 |   null  |
| CURLE_UNSUPPORTED_PROTOCOL|1  | 错误的协议 |  未支持的协议。此版cURL 不支持这一协议   |
| CURLE_FAILED_INIT|2  | 初始化代码失败 | 初始化失败    |
| CURLE_URL_MALFORMAT|3  | URL格式不正确 |URL 格式错误。语法不正确。     |
| CURLE_NOT_BUILT_IN|4  | 请求协议错误 |   null  |
| CURLE_COULDNT_RESOLVE_PROXY|5  | 无法解析代理 |     无法解析代理。无法解析给定代理主机|
| CURLE_COULDNT_RESOLVE_HOST|6  | 无法解析主机地址 |  无法解析主机。无法解析给定的远程主机   |
| CURLE_COULDNT_CONNECT|7  | 无法连接到主机 |  无法连接到主机。   |
| CURLE_FTP_WEIRD_SERVER_REPLY|8 | 远程服务器不可用 |  FTP 非正常的服务器应答。cURL 无法解析服务器发送的数据   |
| CURLE_REMOTE_ACCESS_DENIED|9  | 访问资源错误 |     FTP 访问被拒绝。服务器拒绝登入或无法获取您想要的特定资源或目录。最有可能的是您试图进入一个在此服务器上不存在的目录。|
| CURLE_FTP_ACCEPT_FAILED|10  | FTP接受失败 |     在等待服务器的连接时，一个主动FTP会话使用，被送到控制连接或类似的错误代码。|
| CURLE_FTP_WEIRD_PASS_REPLY|11|FTP密码错误|FTP 非正常的PASS 回复。cURL 无法解析发送到PASS 请求的应答|
| CURLE_FTP_WEIRD_PASV_REPLY|13|结果错误|FTP 非正常的的PASV 应答，cURL 无法解析发送到PASV 请求的应答|
| CURLE_FTP_WEIRD_227_FORMAT|14|FTP回应PASV命令|FTP 非正常的227格式。cURL 无法解析服务器发送的227行|
| CURLE_FTP_CANT_GET_HOST|15|内部故障|FTP 无法连接到主机。无法解析在227行中获取的主机IP|
| CURLE_FTP_COULDNT_SET_TYPE|17|设置传输模式为二进制|FTP 无法设定为二进制传输。无法改变传输方式到二进制。|
| CURLE_PARTIAL_FILE|18|文件传输短或大于预期|部分文件。只有部分文件被传输。|
| CURLE_FTP_COULDNT_RETR_FILE|19|RETR命令传输完成|FTP 不能下载/访问给定的文件， RETR (或类似)命令失败。|
| CURLE_QUOTE_ERROR|21|命令成功完成|FTP quote 错误。quote 命令从服务器返回错误|
| CURLE_HTTP_RETURNED_ERROR|22|返回正常|HTTP 找不到网页。找不到所请求的URL 或返回另一个HTTP 400或以上错误此返回代码只出现在使用了-f/--fail 选项以后。|
| CURLE_WRITE_ERROR|23|数据写入失败|写入错误。cURL 无法向本地文件系统或类似目的写入数据。|
| CURLE_UPLOAD_FAILED|25|无法启动上传|FTP 无法STOR 文件。服务器拒绝了用于FTP 上传的STOR 操作。|
| CURLE_READ_ERROR|26|回调错误|读错误。各类读取问题。|
| CURLE_OUT_OF_MEMORY|27|内存分配请求失败|内存不足。内存分配请求失败。|
| CURLE_OPERATION_TIMEDOUT|28|访问超时|操作超时。到达指定的超时期限条件。|
| CURLE_FTP_PORT_FAILED|30|FTP端口错误|FTP PORT 失败。PORT 命令失败。并非所有的FTP 服务器支持PORT 命令，请尝试使用被动(PASV)传输代替！|
| CURLE_FTP_COULDNT_USE_REST|31|FTP错误|FTP 无法使用REST 命令。REST 命令失败。此命令用来恢复的FTP 传输|
| CURLE_RANGE_ERROR|33|不支持请求|HTTP range 错误。range "命令"不起作用|
| CURLE_HTTP_POST_ERROR|34|内部发生错误|HTTP POST 错误。内部POST 请求产生错误|
| CURLE_SSL_CONNECT_ERROR|35|SSL/TLS握手失败|SSL 连接错误。SSL 握手失败|
| CURLE_BAD_DOWNLOAD_RESUME|36|下载无法恢复|FTP 续传损坏。不能继续早些时候被中止的下载|
| CURLE_FTP_ACCEPT_FAILED|37|文件权限错误|文件无法读取。无法打开文件。权限问题|
| CURLE_LDAP_CANNOT_BIND|38|LDAP可没有约束力|LDAP 无法绑定。LDAP 绑定(bind)操作失败|
| CURLE_LDAP_SEARCH_FAILED|39|LDAP搜索失败|LDAP 搜索失败|
| CURLE_FUNCTION_NOT_FOUND|41|函数没有找到|功能无法找到。无法找到必要的LDAP 功能|
| CURLE_ABORTED_BY_CALLBACK|42|中止的回调|由回调终止。应用程序告知cURL 终止运作|
| CURLE_BAD_FUNCTION_ARGUMENT|43|内部错误|内部错误。由一个不正确参数调用了功能|
| CURLE_INTERFACE_FAILED|45|接口错误|接口错误。指定的外发接口无法使用|
| CURLE_UNKNOWN_OPTION|47|过多的重定向|过多的重定向。cURL 达到了跟随重定向设定的最大限额|
| CURLE_OBSOLETE50|48|无法识别选项|指定了未知TELNET 选项|
| CURLE_PEER_FAILED_VERIFICATION|49|TELNET格式错误|不合式的telnet 选项|
| CURLE_SSL_ENGINE_NOTFOUND|51|远程服务器的SSL证书|peer 的SSL 证书或SSH 的MD5指纹没有确定|
| CURLE_SSL_ENGINE_SETFAILED|52|服务器无返回内容|服务器无任何应答，该情况在此处被认为是一个错误|
| CURLE_SEND_ERROR|53|加密引擎未找到|找不到SSL 加密引擎|
| CURLE_RECV_ERROR|54|设定默认SSL加密失败|无法将SSL 加密引擎设置为默认|
| CURLE_OBSOLETE57|55|无法发送网络数据|发送网络数据失败|
| CURLE_SSL_CERTPROBLEM|56|衰竭接收网络数据|在接收网络数据时失败|
| CURLE_SSL_CIPHER|57|null |null|
| CURLE_SSL_CACERT|58|本地客户端证书|本地证书有问题|
| CURLE_BAD_CONTENT_ENCODING|59|无法使用密码|无法使用指定的SSL密码|
| CURLE_LDAP_INVALID_URL|60|凭证无法验证|peer 证书无法被已知的CA 证书验证|
| CURLE_FILESIZE_EXCEEDED|61|无法识别的传输编码|无法辨识的传输编码|
| CURLE_USE_SSL_FAILED|62|无效的LDAP URL|无效的LDAP URL|
| CURLE_SEND_FAIL_REWIND|63|文件超过最大大小|超过最大文件尺寸|
| CURLE_SSL_ENGINE_INITFAILED|64|FTP失败|要求的FTP 的SSL 水平失败|
| CURLE_LOGIN_DENIED|65|倒带操作失败|发送此数据需要的回卷(rewind)失败|
| CURLE_TFTP_NOTFOUND|66| CURLE_FTP_ACCEPT_FAILED|SSL引擎失败|初始化SSL 引擎失败|
| CURLE_TFTP_PERM|67|服务器拒绝登录|用户名、密码或类似的信息未被接受，cURL 登录失败|
| CURLE_REMOTE_DISK_FULL|68|未找到文件|在TFTP 服务器上找不到文件|
| CURLE_TFTP_ILLEGAL|69|无权限|TFTP 服务器权限有问题|
| CURLE_TFTP_UNKNOWNID|70|超出服务器磁盘空间|TFTP 服务器磁盘空间不足|
| CURLE_REMOTE_FILE_EXISTS|71|非法TFTP操作|非法的TFTP 操作|
| CURLE_TFTP_NOSUCHUSER|72|未知TFTP传输的ID|未知TFTP 传输编号(ID) |
| CURLE_CONV_FAILED|73|文件已经存在|文件已存在(TFTP) |
| CURLE_CONV_REQD|74|错误TFTP服务器|无此用户(TFTP) |
| CURLE_SSL_CACERT_BADFILE|75|字符转换失败|字符转换失败|
| CURLE_REMOTE_FILE_NOT_FOUND|76|必须记录回调|需要字符转换功能|
| CURLE_SSH|77|CA证书权限| CURLE_FTP_ACCEPT_FAILED|读SSL 证书出现问题(路径？访问权限？ ) |
| CURLE_SSL_SHUTDOWN_FAILED|78|URL中引用资源不存在|URL中引用的资源不存在|
| CURLE_AGAIN|79|错误发生在SSH会话|SSH 会话期间发生一个未知错误|
| CURLE_SSL_CRL_BADFILE|80|无法关闭SSL连接|未能关闭SSL 连接|
| CURLE_SSL_ISSUER_ERROR|81|服务未准备|null |

###Thanks
thanks to [yanglinjingshu](http://blog.csdn.net/yanglinjingshu/article/details/45605381)
###License

    Copyright 2016 Nightonke

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
