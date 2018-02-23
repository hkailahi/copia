awk '{ sum += $1 } END { print sum }' four_recipient_delivery.txt
978863
awk '{ sum += $1 } END { print sum }' parallel_four_recipient_matches.txt
978863

It was taking around 5 seconds on my laptop to compute a four recipient matches for each pickup. With ~200 pickups that's 1000 seconds or around 17 minutes! This isn't including the five or six recipient matches, which I don't think my computer will be able to handle.

(Edit: Using link I found from the References section in 'Effective Java - 3rd Edition' called [*When to use parallel streams*](http://gee.cs.oswego.edu/dl/html/StreamParallelGuidance.html), I determined that parallel streams are worth using on this problem.
// TODO - explain
1. Pickups is splittable
2. Each pickup in pickups is independent of the others
3. Algorithm is computationally expensive  (N >= 10000)
4. MatchMap can be turned to ConcurrentHashMap to synchronize on writes
Using parallel streams allows me to compute four recipient matches in less than five minutes, but five and six are still not available.)

If I choose to only run getFourRecipientMatches() on for pickups that don't have a single recipient solution, the result is easily computed / manageable. This is because:
1. Pickups that have a lot of single matches are likely to be easier to match, and thus will have more four recipient matches. Once a four match recipient match is found, addition computation will need to be performed (List creation, List sorting, Comparison with HashSet key, matchMap put, etc.).
2. The filtered list of pickups is an order of magnitude less than the full pickup list.

Filtered Pickup Four Recipient Matches:
47
0   <-- no food
2286
0   <-- no food
384
0   <-- James Whitehouse = the problem child
56
0   <-- no food
550
4
5602
42
