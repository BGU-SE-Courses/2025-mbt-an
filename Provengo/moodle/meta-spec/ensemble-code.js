// @provengo summon ctrl

/**
 * Checks if a given event pair is in the list of blocked pairs.
 * @param {Array} pair The event pair to check.
 * @param {Array} blockedPairs The list of blocked pairs.
 * @returns {boolean} True if the pair is blocked, false otherwise.
 */
function isBlockedPair(pair, blockedPairs) {
    return blockedPairs.some(
        blockedPair => JSON.stringify(pair) === JSON.stringify(blockedPair)
    );
}

/**
 * Generates pairs of events for two-way coverage while excluding blocked pairs.
 * This ensures we only create valid two-way sequences for coverage.
 *
 * @param {Event[]} events An array of domain-specific events.
 * @param {Array} blockedPairs A list of event pairs that are invalid/contradictory.
 * @returns {Array} All valid pairs of events that we want to see in the test suite.
 */
function generateFilteredTwoWayCoverage(events, blockedPairs) {
    const pairs = [];
    for (let i = 0; i < events.length; i++) {
        for (let j = 0; j < events.length; j++) {
            const pair = [events[i], events[j]];
            if (!isBlockedPair(pair, blockedPairs)) {
                pairs.push(pair);
            }
        }
    }
    return pairs;
}

/**
 * Domain events for a Moodle-like story.
 * Adjust these to match your real events.
 */
const events = [
    Event('setup_end'),                // System setup has completed
    Event('admin_login_complete'),     // Admin has successfully logged in
    Event('student_login_complete'),   // Student has logged in
    Event('student_moving_complete'),  // Student navigated to quiz or next page
    Event('admin_removing_complete'),  // Admin removed a student from group
    Event('coordinateActions'),        // Coordinated event after both finished
];

/**
 * Blocked pairs: pairs of events that should not occur in immediate sequence.
 * Adjust as needed based on your domain logic.
 */
const blockedPairs = [
    // Example constraints on setup
    [Event('setup_end'), Event('admin_login_complete')],    // Maybe admin must log in BEFORE setup ends
    [Event('setup_end'), Event('student_login_complete')],  // Or student can't log in until after setup is done

    // Admin-student conflicts
    [Event('admin_login_complete'), Event('student_login_complete')],
    [Event('admin_removing_complete'), Event('student_moving_complete')],

    // Coordination constraints
    [Event('coordinateActions'), Event('setup_end')],

    // Add any other domain-specific blocked pairs here...
];

/**
 * Generate the list of valid two-way pairs from our domain events,
 * excluding those in 'blockedPairs'.
 */
const twoWayPairs = generateFilteredTwoWayCoverage(events, blockedPairs);

/**
 * Ranks a test suite by how many valid two-way pairs it covers.
 * We do this by looking at adjacent events in each test (trace),
 * and checking if they match one of the desired pairs.
 *
 * @param {Event[][]} ensemble - An array of test traces (each trace is an array of events).
 * @returns {number} The percentage of two-way pairs covered in the test suite.
 */
function rankByTwoWayCoverage(ensemble) {
    // Convert all valid pairs to JSON strings for easy comparison
    const uncoveredPairs = new Set(twoWayPairs.map(pair => JSON.stringify(pair)));

    // For each test trace
    for (let testIdx = 0; testIdx < ensemble.length; testIdx++) {
        let test = ensemble[testIdx];
        // Check adjacent events
        for (let eventIdx = 0; eventIdx < test.length - 1; eventIdx++) {
            let pair = [test[eventIdx], test[eventIdx + 1]];
            let pairString = JSON.stringify(pair);
            // If it's one of our desired pairs, mark it as covered
            if (uncoveredPairs.has(pairString)) {
                uncoveredPairs.delete(pairString);
            }
        }
    }

    // Calculate how many valid pairs were covered
    const totalPairs = twoWayPairs.length;
    const coveredPairs = totalPairs - uncoveredPairs.size;
    return (coveredPairs / totalPairs) * 100; // Return a percentage
}

/**
 * The main ranking function for two-way coverage.
 * Provengo will call this function by default if configured in .provengo or CLI.
 *
 * @param {Event[][]} ensemble The generated test suite (ensemble).
 * @returns {number} The two-way coverage percentage.
 */
function rankingFunction(ensemble) {
    return rankByTwoWayCoverage(ensemble);
}







//////////////////////////////domain
////  domain : Moodle
//
///**
// * List of events "of interest" that we want test suites to cover.
// */
//const domain = [
//    Event('setup_end'),
//    Event('admin_login_complete'),
//    Event('student_login_complete'),
//    Event('student_moving_complete'),
//    Event('admin_removing_complete'),
//    Event('coordinateActions'),
//    Event('teacher_graded_assignment')
//];
//
///**
// * Ranks test suites by how many events from the GOALS array were met.
// * The more goals are met, the higher the score.
// *
// * It makes no difference if a goal was met more than once.
// *
// * @param {Event[][]} ensemble The test suite to be ranked.
// * @returns Number of events from GOALS that have been met.
// */
//function rankByMetGoals(ensemble) {
//    const unreachedGoals = [];
//    for (let idx = 0; idx < domain.length; idx++) {
//        unreachedGoals.push(domain[idx]);
//    }
//
//    for (let testIdx = 0; testIdx < ensemble.length; testIdx++) {
//        let test = ensemble[testIdx];
//        for (let eventIdx = 0; eventIdx < test.length; eventIdx++) {
//            let event = test[eventIdx];
//            for (let ugIdx = unreachedGoals.length - 1; ugIdx >= 0; ugIdx--) {
//                let unreachedGoal = unreachedGoals[ugIdx];
//                if (unreachedGoal.contains(event)) {
//                    unreachedGoals.splice(ugIdx, 1);
//                }
//            }
//        }
//    }
//
//    return domain.length - unreachedGoals.length;
//}
//
///**
// * Ranks potential test suites based on the percentage of goals they cover.
// * Goal events are defined in the domain array above. An ensemble with rank
// * 100 covers all the goal events.
// *
// * Multiple ranking functions are supported - to change ranking function,
// * use the `ensemble.ranking-function` configuration key, or the
// * --ranking-function <functionName> command-line parameter.
// *
// * @param {Event[][]} ensemble the test suite/ensemble to be ranked
// * @returns the percentage of goals covered by `ensemble`.
// */
//function rankingFunction(ensemble) {
//    // How many goals did `ensemble` hit?
//    const metGoalsCount = rankByMetGoals(ensemble);
//    // What percentage of the goals did `ensemble` cover?
//    const metGoalsPercent = metGoalsCount / domain.length;
//    return metGoalsPercent * 100; // convert to human-readable percentage
//}
//
