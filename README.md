# ImgurUploadAPI
this project is for uploading image to imgur

# To install
mvn clean install

# To start this service, execute the below command:
mvn spring-boot:run

# Default user name and pwd
admin/admin

# url to start with
http://localhost:7654/swagger-ui/

# adding user
POST ​/user adduser
# viewing All user
GET /user getUsers

this will give all registered user and images associated with user
# viewing a perticular user with Login id
GET /user/{loginid}
this will give  user detail and images associated with user
# logged in user can add images those will be linked with user
POST /upload

its response heager will contain a ref property and its value will be link to that image. User can copy that image browser and view tha image uplaod.

# Delete image
DELETE /getImage/{id}
id you can get it from GET /user/{loginid}

