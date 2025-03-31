# Question Collector 

## Overview

**Question Collector** is an application developed to integrate s1mulado with the public ENEM API. It collects and processes exam questions, mapping them to a format compatible with the **s1mualdo** application. Additionally, the application manages associated question media files (images), downloading and uploading them to a public AWS S3 bucket. After the upload, the system updates the data model with the new image links.

## Features

- Collects ENEM questions from a public API.
- Maps questions to a format compatible with the **s1mualdo** application.
- Downloads associated images and uploads them to an AWS S3 public bucket.
- Updates the data model with the links to the images stored in S3.
- Automated and efficient process for managing exam questions and related media files.
