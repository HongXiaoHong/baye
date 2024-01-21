import subprocess

# subprocess.call 后台运行一个进程
# creationflags=subprocess.CREATE_NEW_CONSOLE 参数创建一个 cmd 的终端
subprocess.check_call('python ./__init__.py', shell=True)
