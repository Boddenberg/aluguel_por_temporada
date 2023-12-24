resource "aws_sns_topic" "topic_sns" {
  name            = var.sns_name
}

resource "aws_sns_topic_policy" "sns_policy" {
  arn = aws_sns_topic.topic_sns.arn

  policy = data.aws_iam_policy_document.sns_topic_policy.json
}

data "aws_iam_policy_document" "sns_topic_policy" {
  policy_id = "__default_policy_ID"

  statement {
    actions = [
      "SNS:Subscribe",
      "SNS:SetTopicAttributes",
      "SNS:RemovePermission",
      "SNS:Receive",
      "SNS:Publish",
      "SNS:ListSubscriptionsByTopic",
      "SNS:GetTopicAttributes",
      "SNS:DeleteTopic",
      "SNS:AddPermission",
    ]

    resources = [
      aws_sns_topic.topic_sns.arn,
    ]

    effect = "Allow"
  }
}