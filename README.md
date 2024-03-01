CREATE SCHEMA `moonsocialapp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;

This is a microservice project

## Tech stack
Springboot + hibernate + MySQL + liquibase

Here is the frontend, still updating 
https://github.com/Amber916Young/flutter-moon-social-app

hot
https://xie.infoq.cn/article/a5cefe30dafcd09c983fd9591

flutter theme
https://rydmike.com/flexcolorscheme/themesplayground-latest/

## 技术栈
Springboot + hibernate + mysql + liquibase
Elasticsearch 全局搜索

前端 flutter

### 后端架构
microservices 微服务
- User service (authentication, user profiles)
- Discussion service (handling discussions, posts, tags/categoryddd)
- Friend service (managing friendships)
- Notification service (sending notifications)
- Media service (handling images, videos)
- Messaging service (for direct messages)

## Auth module

### /api/auth

#### POST

- [x] login
- [x] register
- [ ] forget-password
- [ ] reset-password
- 
#### DELETE
- [ ] DELETE {userId}

### /api/users

#### GET

- [x] get user info
- [ ] {userId}  -- check other user
- [ ] follow-unfollow/{userId}  -- follow/unfollow

#### PUT

- [ ]update UserInfo 

#### POST

- [ ]update UserInfo


## Discussion module

### /api/discussions 

**GET**
- [x] getDiscussionList 分页获取首页帖子
- [x] getTopDiscussionList/sticky  获取置顶帖子
- [x] getPostsByDiscussionId/{discussionId}  获取某个帖子
- [ ] getDiscussionListByTag(/{tagId}) 根据tag标签获取帖子list



### /api/discussions/posts

**POST**  需要关联auth模块
发帖，title，content

**GET**
- [x] /{discussionId}) 分页获取帖子内容,包括点赞，回复等


### /api/collections

**GET**
- [ ] getCollection 获取自己的收藏夹
- [ ] getCollectionByUserId 获取别人的收藏夹

**POST**
- [ ] createCollection 获取自己的收藏夹



### /api/tags
树形结构
- tag1
  -tag1-2
  -tag1-2
- tag2
  -tag2-1
- tag3
  -tag3-1           
  -tag3-2      
  -tag3-3


**GET**
需要考虑返回tagDTO的那些参数是用户可见的

- [ ] getParentTags 获取parent标签 （这个稍微简单一点）
- [ ] getTags 获取全部标签 （树形结构，需要用到一些算法，比如递归）

