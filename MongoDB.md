# find(), findOne(), insertOne(), insertMany()

### Connect to mongodb:
`mongosh`

### findOne():
```javascript
// normal find one
db.{collection}.findOne()
db.posts.findOne()

// with condition
db.{collection}.findOne({title: "Hello"})
db.posts.findOne({title: "Hello"})
```

### find():
```javascript
// normal find
db.{collection}.find()
db.posts.find()

// with condition
db.{collection}.find({title: "Hello"})
db.posts.find({title: "Hello"})

// query, projections, options
db.{collection}.find(<query>, <projection>, <options>)
db.posts.find(
    {likes: {$gt: 0}, tags: {$in: ["news"]}}, // query: searches for documents where the likes field is greater than ($gt) 0 [$gt, $in, $gte, $lt, $lte, $eq, $ne, $nin]
    {category: 1, title: 1}, // projection: indicate whcih fields you want to include in the retrieved documents
    {sort: {category: -1}} // options: -1 for descending order and 1 for ascending order
)

// find query with $and
db.posts.find({
    $and: [
        {likes: {$gt: 0}},
        {category: {$eq: "News"}}
    ]
})
```

### insertOne():
```javascript
db.{collection}.insertOne(jsonData)
db.posts.insertOne( 
	{ title: "Election", 
		body: "Election of president for USA", 
		category: "government", 
		likes: 6, 
		tags: ["news", "trending"] 
	} 
)
// this will also create collection "posts" directly
```

### insertMany():
```javascript
db.{collection}.insertMany(listOfJsonData)
db.posts.insertMany(
	[
		{
			title: "Technology",
			body: "Cloud computing",
			date: Date()
		},
		{
			title: "School",
			body: "miu school started...",
			date: Date(),
			like: 5
		}
	]
)
// this will also create collection "posts" directly
```


# MongoDB Cheatseet

### Coneect to mongodb
`mongosh`

### Show list of databases
`show dbs`

### Use a certain database
```javascript
// use {databaseName}
use admin
use local
use config
```

### Show collections in database
```javascript
// db.{databaseName}.find()
db.startup_log.find()
```

### Show users in mongoDB
`show users`

### How to add use to mongoDB database (this user here is not collection)
```javascript
db.createUser(
	{
		user: "sanjeev",
		/** rather than storing password right away, command line will ask for the password after done with this **/
		pwd: passwordPrompt(),
		roles: [
			{
				role: "readWrite",
				db: "test"
			}
		]
	}
)
```

### Create a new database
```javascript
// use {databaseName}
use blog
// we cannot see the database when run "show dbs" command
// we will see the database in "show dbs" after a collection is created
```

### Create collection
```javascript
db.createCollection("posts")
```

### Drop Database
```javascript
db.dropDatabase()
```

### Insert one data into collection
```javascript
db.posts.insertOne( 
	{ title: "Election", 
		body: "Election of president for USA", 
		category: "government", 
		likes: 6, 
		tags: ["news", "trending"] 
	} 
)

// this will also create collection "posts" directly
```

### Insert many data into collection
```javascript
db.posts.insertMany(
	[
		{
			title: "Technology",
			body: "Cloud computing",
			date: Date()
		},
		{
			title: "School",
			body: "miu school started...",
			date: Date(),
			like: 5
		}
	]
)

// this will also create collection "posts" directly
```