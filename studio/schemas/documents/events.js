export default {
    title: "Events",
    name: "events",
    type: "document",
    fields: [
        {
            title: "Event name",
            name: "title",
            type: "string"
        },
        {
            title: "Event date",
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
                        type: "cities"
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
            to: [{type: "creators"}]
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
                    type: "categories"
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
            title: "Event speakers",
            name: "speakers",
            type: "array",
            of: [{
                type: "reference",
                to: [{
                    type: "speakers"
                }]
            }]
        },
    ]
}