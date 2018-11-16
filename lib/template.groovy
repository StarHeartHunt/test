String toString(fileName, binding) {
  def template_dir = "${JENKINS_HOME}/workspace/${JOB_NAME}/template"
  def f = new File("${template_dir}/${fileName}")
  def engine = new groovy.text.SimpleTemplateEngine()
  def template = engine.createTemplate(f).make(binding)
  return template.toString()
}

String getValidateError(errorCode, paramName) {
  def ERROR = load("constant/error.groovy").getAll()
  def binding = [
    "param_name": paramName,
    "error_message": ERROR[errorCode]

  ]
  def filePath = "${JENKINS_HOME}/workspace/${JOB_NAME}/template/error/validate.template"
  def f = new File(filePath)
  def engine = new groovy.text.SimpleTemplateEngine()
  def template = engine.createTemplate(f).make(binding)
  return template.toString()
}

String getValidateAllError(errorMessages) {
  wrap([$class: 'BuildUser']) {
    def ERROR = load("constant/error.groovy").getAll()
    def binding = [
      "user_name": BUILD_USER,
      "job_name": JOB_NAME,
      "job_url": "${env.JENKINS_URL}job/${JOB_NAME}/${BUID_NUMBER}/",
      "error_messages": errorMessages
    ]
    def filePath = "${JENKINS_HOME}/workspace/${JOB_NAME}/template/error/validate_all.template"
    def f = new File(filePath)
    def engine = new groovy.text.SimpleTemplateEngine()
    def template = engine.createTemplate(f).make(binding)
    return template.toString()
  }
}
String getSafetyError(errorCode) {
  wrap([$class: 'BuildUser']) {
    def ERROR = load("constant/error.groovy").getAll()
    def binding = [
      "user_name": BUILD_USER,
      "job_number": BUILD_NUMBER, 
      "job_name": JOB_NAME,
      "job_url": "${env.JENKINS_URL}job/${JOB_NAME}/${BUID_NUMBER}/",
      "error_message": ERROR[errorCode]
    ]
    def filePath = "${JENKINS_HOME}/workspace/${JOB_NAME}/template/error/validate_all.template"
    def f = new File(filePath)
    def engine = new groovy.text.SimpleTemplateEngine()
    def template = engine.createTemplate(f).make(binding)
    return template.toString()
  }
}

return this