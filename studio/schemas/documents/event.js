export default {
    title: "Event",
    name: "event",
    type: "document",
    fields: [
        {
            title: "Event name",
            name: "title",
            type: "string"
        },
        {
            title: "Slug",
            name: "slug",
            type: "slug",
            options: {
                source: "title"
            }
        },
        {
            title: "Event start",
            name: "date",
            type: "datetime",
        },
        {
            title: "Event end",
            name: "date",
            type: "datetime",
        },
        {
            title: "Thumbnail",
            name: "thumbnail",
            type: "image"
        },
        {
            title: "Place",
            name: "place",
            type: "object",
            fields: [
                {
                    title: "City",
                    name: "city",
                    type: "reference",
                    to: [{
                        type: "city"
                    }]
                },
                {
                    title: "Place",
                    name: "place",
                    type: "string"
                },
                {
                    title: "Address",
                    name: "address",
                    type: "string"
                }
            ]
        },
        {
            title: "Event Creator",
            name: "eventCreator",
            type: "reference",
            to: [{type: "creator"}]
        },
        {
            title: "Entry price",
            name: "price",
            type: "object",
            fields: [
                {
                    title: "Free",
                    name: "free",
                    type: "boolean",
                    
                },
                {
                    title: "Amount",
                    name: "amount",
                    type: "number",
                    hidden: ({ parent, value }) => !value && parent?.free // Must be fixed
                },
            ]
        },
        {
            title: "Description",
            name: "description",
            type: "text"
        },
        {
            title: "Event category",
            name: "eventCategory",
            type: "array",
            of: [{
                type: "reference",
                to: [{
                    type: "category"
                }]
            }]
        },
        {
            title: "Age limit",
            name: "ageLimit",
            type: "object",
            fields: [
                {
                    title: "Age limit",
                    name: "ageLimitToggle",
                    type: "boolean"
                },
                {
                    title: "Age",
                    name: "age",
                    type: "number",
                    hidden: ({ parent, value }) => !value && parent?.ageLimitToggle // Must be fixed
                }
            ]
        },
        {
            title: "Event speaker",
            name: "speaker",
            type: "array",
            of: [{
                type: "reference",
                to: [{
                    type: "speaker"
                }]
            }]
        },
    ]
}