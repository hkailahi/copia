# Initial Thoughts + Activity Log

### Summary

The first major finding I had was the one-way, 1-to-many relationship between a pickup and recipients. Initially I thought that the algorithm needed would be a two-sided scheduling problem. Thankfully it becomes much simpler by representing pickups (domain) map to recipients (codomain) as no pickup is reliant on information between a recipient's mapping to itself or other pickups.

I decided to let a database handle parsing the CSV rather than rolling my own parser. Postgres supports a ```timestamptz```, or ```timestamp with time zone```, datatype which handles ISO 8601 temporal data, including the UTC offset.

// TODO describe notes below

### Notes

- Is latest time inclusive? If I have 9 AM pickup (thus 10 latest) do I need recipients to be open from 9-10 and 10-11?
- Loaded data into Postgres and inspected
  - Noticed -8:00 and -7:00 appended to timezones
    - ISO 8601 UTC offset
  - Noticed all in America/Los_Angeles (as stated in spec)
- Timezone
  - Figured out what Timezone UTC offset was
    - Postgres' Timezonetz datatype
  - worldtimebuddy.com
  - 40ish entries have -7, with others -8
    - daylight savings happened on Nov 6 in 2016?
      - https://www.timeanddate.com/time/change/usa/new-york?year=2016
        ```
        Nov 6, 2016 - Daylight Saving Time Ended
        When local daylight time was about to reach
        Sunday, November 6, 2016, 2:00:00 am clocks were turned backward 1 hour to
        Sunday, November 6, 2016, 1:00:00 am local standard time instead.

        Sunrise and sunset were about 1 hour earlier on Nov 6, 2016 than the day before. There was more light in the morning.

        Also called Fall Back and Winter Time.

        More info:
        USA & Canada: DST Ends on Nov 6, 2016
        ```
- Came up with OO design sketch and rough/first-take algo
  - [Algorithm and OO design sketch](docs/img/initial_oo_algo_sketch.jpg)
  - OO
    - Since both "pickup" and "recipient" both hold a number of matching columns, I decided to make both a subclass of "user" class
    - I renamed "pickup" to "provider" as I believe it is more intuitive
  - Algo
    - Pseudo-code
    ```java
    public List<Recipient> findSortedMatches(Provider p, Recipients allRecips) { // allRecips = rs if FP
        List sortedMatches = allRecips.stream()
                              .filter(r -> distance(r,p) < 5)  // < or <=, and will have to impl dist fn
                              .sort(r -> dist(r,p))            // descending
                              .filter(r -> !r.restrictions.contains(p.categories)) // Struct will be diff
                              .sort(r -> mostMatches) // see MOST-MATCH-NOTES
                              .filter(r -> r.isOpenAt(p.pickupTime)) // will have to impl isOpen
                              .sort()
                              .collect(Collectors.toList()); // this is how I go from stream -> list
        return sortedMatches;
      } // sort is O(n log n), sortedMatches is O(n) space
      ```
      - MOST-MATCH-NOTES: This is weird, maybe I want most matches for simplicity or maybe I want 6-dimension sort requiring smallest number of drop-offs or I want that but favor shortest distance chain with multiple deliveries over multiple
        - this could go after timecheck and incorporate it, depends on what I decide
      - Could do all sorts at bottom or in another algo
      ```java
      public List<Recipient> findMatches(Provider p, Recipients allRecips) { // allRecips = rs if FP
          List sortedMatches = allRecips.stream()
                                .filter(r -> distance(r,p) < 5)  // < or <=, and will have to impl dist fn
                                .filter(r -> !r.restrictions.contains(p.categories)) // Struct will be diff
                                .filter(r -> r.isOpenAt(p.pickupTime)) // will have to impl isOpen
                                .collect(Collectors.toList()); // this is how I go from stream -> list
          return sortedMatches;
      }
      public List<Recipient> sortMatches(Provider p, Recipients matches) { // allRecips = rs if FP
        List sortedMatches = matches.stream()
                                .sort(r -> dist(r,p))            // descending
                                .sort(r -> mostMatches) // see MOST-MATCH-NOTES
                                .sort(r -> time)
                                .collect(Collectors.toList()); // this is how I go from stream -> list
        return sortedMatches;
      }
      ```
        - sortMatches is likely more complicated
- Program Run steps

```
1. Pull all providers and recipients from DB and store in memory
  a. Small data source, would be different if it was huge
2. Feed algo, which iterates through each provider - O(n)
  a. Given a provider, look through all recipients and find if they match - O(n)
  b. Sort - O(n log n)
  c. Created sorted matches list - O(n) space
  d. (a), (b), (c) are nested in (2), so O(n^2 log n) runtime with O(n^2) space
    - it's probably more because restrictions .contains() categories most likely isn't O(1) but O(n)
    - isOpen may be O(n)
    - Dist will be O(1) though
    - So maybe O(n^3) - cubic functions
      - We should try to do better
3. Then take matches and write to database
4. Serialize db table as CSV either in SQL or by pulling it back out and writing in app server
```

  - Using Java Stream API to compose/chain functions and find a sorted (by most favorable to least) list for each pickup/provider
    - Allows me to chain functions that I can write/test independently
      - deterministic
    - I can also easily change the algorithm by chaining parameters, adding new functions, changing the order of operations, etc.
  - Assumptions I can make or made:
    - I assume infinite drivers who can pickup everything from a provider all at once, who make multiple stops within the 5 mile radius until everything is dropped off.
    - Maybe some providers cannot be picked up from
      - Ex.
        - no recipient is within 5 miles
        - no one is open during the time
        - no one accepts the food they are provided
        - some combo of above
          - Ex.
            - no one within 5 miles who is open accepts the food they are provided
- Categories vs Restrictions
  - Something to be aware of
  - Providers list what they CAN provide, while recipients list what they CAN'T receive
- Initial thoughts on resulting CSV/DB table format
  - [DB Tables Sketch](docs/img/initial_db_sketch.jpg)
    - Make two tables
    - Recipent_Match_Table
      - Has all matches with a match_id, with a match_weight/weight which we'll use to sort matches from most favorable to least favorable.
      - Also meta-info.
      - Match has one pickup (pickup_id/provider)
      - Match can contain up to 6 recipient. Example: one only accepts meat, other only accepts frozen, etc where provider needs to deliver one of every category
        - Match thus has recip_1, recip_2, recip_3, recip_4, recip_5, recip_6
    - Pick_Up_Delivery
      - in essence, the match where an actual delivery was/will be made
        - thus, row count should be less than or equal to pickups row count
      - has one (or a sorted list of ) match from recipient_match_table, which is the delivery that actually happens. Also meta-info.
