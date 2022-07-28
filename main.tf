terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.49"
    }
  }

  required_version = ">= 0.14.9"
}

provider "aws" {
  profile = "local"
  region = "eu-central-1"
  access_key = "foo"
  s3_force_path_style = true
  secret_key = "bar"
  skip_credentials_validation = true
  skip_metadata_api_check = true
  skip_requesting_account_id = true

  endpoints {
    sqs="http://localhost:4566"
  }

}

resource "aws_sqs_queue" "userTopic" {
  name = "userTopic"
}

resource "aws_sqs_queue" "articleTopic" {
  name = "articleTopic"
}

resource "aws_sqs_queue" "emailTopic" {
  name = "emailTopic"
}

resource "aws_sqs_queue" "commentTopic" {
  name = "commentTopic"
}




