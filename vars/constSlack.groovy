// package constant
// チャンネル名とJenkinsで管理しているCredentialIDを紐付ける情報
// Slack通知に必要なトークン情報はJenkins側で管理
def channelCredentialIds(channel) {
    def settings = [
      '#hooktest': 'approval_slack_token'
      ]
    return settings[channel]
}