# webcrawler-in-java

## Table of contents

* [Description](#description)
* [Usage](#usage)
* [Issues](#issues)

##Description
This is java program that downloads all the mails in given
particular year from http://mail-archives.apache.org/mod_mbox/maven-users/

##Usage
compile and execute App.java file

you'll be prompted to "enter the year" (from which year you need to download emails).

Then it starts downloading all the emails from that year.

Once the downloding is completed, you can find files created based on number of emails in that year.

##Issues

Once this code is compiled for the first time all the emails get downloaded into files in your current directory.

Later when you attempted to download emails from other year,then the code is written in such a way that
it removes existing files and starts downloding those new mails.

The code for removing the files is written in separate class named "DeleteFiles.java" which is extended by "App.java".

