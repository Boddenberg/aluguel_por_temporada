variable "sns_name" {
  type        = string
  description = "Nome do Tópico SNS"
}
variable "bucket_name" {
  type        = string
  description = "Variavel que armazena o nome do Bucket"
}


variable "endpoint_localstack" {
  type        = string
  description = "Variavel responsável por guardar o endpoint da LocalStack"
  default     = "http://localhost:4566"
}

variable "aws_region" {
  type        = string
  description = "Região AWS"
}

variable "owner" {
  type        = string
  description = "Owner do projeto"
}
