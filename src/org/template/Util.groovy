package org.template


String getString(template_name, binding) {
  def text = new File("${pwd()}/src/resource/org/template/${template_name}")
  // def f = new File(filePath)
  def engine = new groovy.text.SimpleTemplateEngine()
  def template = engine.createTemplate(text).make(binding)
  template.toString()
  // return template.toString()
  return "aaaa"
}

return this