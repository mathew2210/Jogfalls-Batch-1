from flask import Flask, request, render_template
import subprocess

app = Flask(__name__)

@app.route('/', methods=['GET', 'POST'])
def index():
    result = ''
    if request.method == 'POST':
        command = request.form['command']
        try:
            result = subprocess.check_output(command, shell=True, stderr=subprocess.STDOUT)
            result = result.decode('utf-8')
        except subprocess.CalledProcessError as e:
            result = e.output.decode('utf-8')
    return render_template('index.html',result=format(result))

if __name__ == '__main__':
    app.run()