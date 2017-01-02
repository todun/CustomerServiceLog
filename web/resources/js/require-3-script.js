/**
 *
 */
$(document).ready(function () { // load json file using jquery ajax

    function loadIssues() {

        $.getJSON('resources/test.json', function (data) {
            var output = '';
            var hasIssue = data.length > 0;

            // issue(s) - test.json IS NOT an empty array
            if (hasIssue) {
                //    there are issues
                console.log('issues');

                // 1. first hide the no-issues template
                $('div.no-issues-template').css('display', 'none');

                // 2. then output the content of the test.json
                $.each(data, function (key, val) {

                    // resolved issues
                    if (val.status === 'Resolved') {
                        output += '<div class="issue-template">';
                        output += '<h3>' + val.title + ' - ' + 'Began at ' + val.beganAt + '</h3>';
                        output += '<h3>' + val.title + ' - ' + 'Resolved at ' + val.resolvedAt + '</h3>';
                        output += '<p>' + val.description + '</p>';
                        output += '<div style="padding-left:50px">'
                        output += '<p>Updates:</p>';

                        $.each(val.updates, function (k, v) {
                            output += '<div class="update-template">';
                            output += '<p>' + v.update + '</p>';
                            output += '<em>By ' + v.by + ' at ' + v.at + '</em>';
                            output += '<hr style="width:50%"/>';
                            output += '</div>';

                        });
                        output += '</div>';
                        output += '<hr/>';
                        output += '</div>';

                        // output result
                        $('div.issue-template').html(output);

                        $('div.update-template').mouseenter(function () {
                            $(this).animate({
                                opacity: '1'
                            });
                        }).mouseleave(function () {
                            $(this).animate({
                                opacity: '0.1'
                            });
                        });
                    }

                });
            } else {
                // no issues - test.json IS an empty array
                console.log('no issues');
                $('div.issue-template').hide();

                // callback to animate no-issues text
                $('div.no-issue-template', function () {
                    function pulsate() {
                        $("div.no-issues-template").animate({
                            opacity: 0.2,
                            fontSize: '-=5em'
                        }, 1000, 'linear').animate({opacity: 1, fontSize: '+=5em'}, 1000, 'linear', pulsate);
                    }

                    pulsate();
                });
            }
        });
    }

    // show templates on link clicked
    $("a[href]").click(function () {
        loadIssues();
        // show hidden results
        $('#results').show();
    });
});
