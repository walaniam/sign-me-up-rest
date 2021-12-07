# sign-me-up-rest
REST service for https://github.com/walaniam/blockchain-developer-bootcamp-final-project

# Deployment
## Heroku
Deploy with [heroku-maven-plugin](https://devcenter.heroku.com/articles/deploying-java-applications-with-the-heroku-maven-plugin)

### Setup
```shell
APP_NAME=<your app name here>
heroku create -a \$APP_NAME --region eu

git remote rm heroku
git remote add heroku https://git.heroku.com/${APP_NAME}.git
```

### Deploy
```shell
mvn clean heroku:deploy
```