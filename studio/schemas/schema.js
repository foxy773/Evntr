import createSchema from 'part:@sanity/base/schema-creator'
import schemaTypes from 'all:part:@sanity/base/schema-type'
import event from './documents/event.js'
import category from './documents/category.js'
import city from './documents/city.js'
import creator from './documents/creator.js'
import speaker from './documents/speaker.js'

export default createSchema({
  name: 'default',
  types: schemaTypes.concat([
    event,
    category,
    city, 
    creator,
    speaker
  ]),
})
