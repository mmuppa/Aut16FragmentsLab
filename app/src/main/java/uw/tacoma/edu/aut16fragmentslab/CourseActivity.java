package uw.tacoma.edu.aut16fragmentslab;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import uw.tacoma.edu.aut16fragmentslab.course.CourseContent;

public class CourseActivity extends AppCompatActivity implements CourseFragment.OnListFragmentInteractionListener,
        CourseDetailFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        if (findViewById(R.id.fragment_container)!= null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new CourseFragment())
                    .commit();

        }

    }

    @Override
    public void onListFragmentInteraction(CourseContent.CourseItem item) {
        // Capture the course fragment from the activity layout
        CourseDetailFragment courseDetailFragment = (CourseDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.course_item_frag);

        if (courseDetailFragment != null) {
            // If courseDetail frag is available, we're in two-pane layout...

            // Call a method in the course detail fragment to update its content
            courseDetailFragment.updateCourseItemView(item);

        } else {
            // If the frag is not available, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected student
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back

            courseDetailFragment = new CourseDetailFragment();
            Bundle args = new Bundle();
            args.putSerializable(CourseDetailFragment.DETAIL_ARG, item);
            courseDetailFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, courseDetailFragment)
                    .addToBackStack(null);

            // Commit the transaction
            transaction.commit();

        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
