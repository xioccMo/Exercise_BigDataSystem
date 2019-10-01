# Gitlab SSH key 设置

## 1 生成 SSH key

  * 查看 SSH key 是否已存在

    ```shell
    ls ~/.ssh
    ```

    若打印出 `id_rsa` 和 `id_rsa.pub` 则表示已有 SSH key

  * 若不存在, 生成 SSH key

    ```shell
    ssh-keygen -o -t rsa -b 4096
    ```

    在询问保存文件和密码时直接按 `Enter` 即可

## 2 添加 SSH key 至 Gitlab 账号

  * 打开 `~/.ssh/id_rsa.pub`, 全选所有内容复制至剪贴板

  * 打开Gitlab网站, 点击右上角用户头像, 选择 Settings. 在 SSH Keys 中将复制的内容粘贴至 `Key` 处. 点击 `Add key` 按钮

  * 检验是否设置成功

    ```shell
    >>> ssh -T git@??? 
    Welcome to GitLab, @username!
    ```
	
	注意："???" , 公网为gitlab.com, 内网为10.11.1.209(需确保登录了内网VPN)
	
    首次连接会询问 `Are you sure you want to continue connecting (yes/no)?`, 输入 `yes` 即可
