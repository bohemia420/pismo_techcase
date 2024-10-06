#!/bin/bash

BASE_URL="http://localhost:8080"

get_account() {
  account_id=$1
  response=$(curl -s -o /dev/null -w "%{http_code}" "$BASE_URL/api/accounts/$account_id")

  if [ "$response" -eq 200 ]; then
    curl -s "$BASE_URL/api/accounts/$account_id"
  elif [ "$response" -eq 404 ]; then
    echo "account_id $account_id not found"
  else
    echo "Error: Unexpected response code $response"
  fi
}

post_account() {
  payload=$1
  echo "Creating a new account with the following payload: $payload"
  curl -X POST "$BASE_URL/api/accounts" -H "Content-Type: application/json" -d "$payload"
  echo # Adding a new line after the response
}

post_transaction() {
  payload=$1
  echo "Creating a new transaction with the following payload: $payload"
  curl -X POST "$BASE_URL/api/transactions" -H "Content-Type: application/json" -d "$payload"
  echo
}

case "$1" in
  --get-account)
    if [ -z "$2" ]; then
      echo "Error: --get-account requires an account ID."
      exit 1
    fi
    get_account "$2"
    ;;
  --post-account)
    if [ -z "$2" ]; then
      echo "Error: --post-account requires a payload."
      exit 1
    fi
    post_account "$2"
    ;;
  --post-transaction)
    if [ -z "$2" ]; then
      echo "Error: --post-transaction requires a payload."
      exit 1
    fi
    post_transaction "$2"
    ;;
  *)
    echo "Usage: $0 {--get-account accountId | --post-account payload | --post-transaction payload}"
    exit 1
    ;;
esac
