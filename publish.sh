#!/usr/bin/env bash
DEFAULT="s3Full"
PROFILE=${AWS_PROFILE:-$DEFAULT}
BUCKET=xplan-integrations
DIR=/deploy/
aws s3  sync $DIR s3://$BUCKET/
