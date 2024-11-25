# MongoDB
## find(), findOne(), insertOne(), insertMany()

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

## Guess Output
```javascript
1. Consider the following documents in a MongoDB collection named students:

[
    {
        _id: ObjectId('5f8d0d55b54764421b7156c1'),
        name: 'Alice',
        age: 23,
        major: 'Computer Science',
        grades: { math: 90, english: 85, history: 88 }
    },
    {
        _id: ObjectId('5f8d0d55b54764421b7156c2'),
        name: 'Bob',
        age: 22,
        major: 'Mathematics',
        grades: { math: 95, english: 80, history: 82 }
    },
    {
        _id: ObjectId('5f8d0d55b54764421b7156c3'),
        name: 'Charlie',
        age: 24,
        major: 'Physics',
        grades: { math: 85, english: 88, history: 90 }
    }
]

Predict the output of this query.

db.students.findOne(
    { "name": "Bob" },
    { "name": 1, "grades.math": 1}
)

=============
   Output
=============
{
  _id: ObjectId('5f8d0d55b54764421b7156c2'),
  name: 'Bob',
  grades: { math: 95 }
}
```

# Slides
[Slides](https://mum0.sharepoint.com/sites/CS489-2024-11A-11D-01/Shared%20Documents/General/Unified%20CS489%20Material_final.pdf)

# Pom.xml
```
<properties>
	<!-- … -->
	<!-- Enables annotation processing in full mode, allowing all annotations to be processed during compilation. -->
	<maven.compiler.proc>full</maven.compiler.proc>
</properties>
```
