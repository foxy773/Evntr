// First, we must import the schema creator
import createSchema from 'part:@sanity/base/schema-creator'
import events from "./documents/event"
import creators from "./documents/creator"
import categories from './documents/category'
import cities from "./documents/city"
import speakers from "./documents/speaker"

// Then import schema types from any plugins that might expose them
import schemaTypes from 'all:part:@sanity/base/schema-type'

import event from './documents/event'
import category from './documents/category'
import city from './documents/city'
import creator from './documents/creator'
import speaker from './documents/speaker'


// Then we give our schema to the builder and provide the result to Sanity
export default createSchema({
  // We name our schema
  name: 'default',
  // Then proceed to concatenate our document type
  // to the ones provided by any plugins that are installed
  types: schemaTypes.concat([
    event,
    creator,
    category,
    city,
    speaker
  ]),
})
