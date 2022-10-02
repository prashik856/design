# UI for kube-easy application

Creating new react js application and start the application
```bash 
# Create new react application
npx create-react-app kube-easy-app
cd kube-easy-app
npm start
```

Install dependencies
```bash 
npm i react-router-dom react-bootstrap bootstrap
```

Adding proxy to npm
```bash
export http_proxy="your-proxy"
npm config set proxy $http_proxy
npm config set https-proxy $http_proxy
```

Remove proxy from npm
```bash
npm config delete proxy
npm config delete https-proxy
```

Reset to default npm configurations
```bash 
echo "" > $(npm config get userconfig)
npm config edit
echo "" > $(npm config get globalconfig)
npm config --global edit
```

Fix broken severities
```bash 
npm audit fix --force
```
