module "s3" {
  source      = "./modules/s3"
  bucket_name = var.bucket_name
}

module "sns" {
  source   = "./modules/sns"
  sns_name = var.sns_name
  sqs_arn  = ""
}