const express = require('express')
const app = express()
const port = 3001

app.use(express.static('/home/user/IdeaProjects/looking-for-a-companion/src/main/resources/static'))

app.get('/', (req, res) => {
    res.sendFile('/home/user/IdeaProjects/looking-for-a-companion/src/main/resources/templates/start-page.html')
})

app.get('/category/', (req, res) => {
    res.sendFile('/home/user/IdeaProjects/looking-for-a-companion/src/main/resources/templates/start-page.html')
})

app.listen(port, () => {
    console.log(`CompanEra application is listening on port ${port}`)
})
