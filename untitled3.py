# -*- coding: utf-8 -*-
"""
Created on Sat Apr 15 16:56:28 2023

@author: 21129
"""

import requests
from lxml import etree
import os
import re
import ssl
import urllib3

urllib3.disable_warnings()
#ssl认证，因为没有证书，直接不显示警告
url='https://www.imdb.com/title/tt0114709/'
#url地址

headers = {'User-Agent':'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36 QIHU 360SE'}
#反爬虫机制，设置自己的请求头
response=requests.get(url=url,headers=headers,verify=False)
#requests方法获取爬取的网页信息，这里print的话会返回一个200表示成功，verify=false表示不验证https证书
page_text = response.text
#获取到text文本
tree = etree.HTML(page_text)
#使用etree。HTML对html文本进行解析
div_list = tree.xpath('//div[@class="ipc-poster ipc-poster--baseAlt ipc-poster--dynamic-width sc-30a29d44-0 dktfIa ipc-sub-grid-item ipc-sub-grid-item--span-2"]/div/img/@src')
#xpath是文本解析方法，之前用的正则表达式也是这里，我看了几张图片的网页代码格式，class的值没有变化，应该是通用的
print(div_list)