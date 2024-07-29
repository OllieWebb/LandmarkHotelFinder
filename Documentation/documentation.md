# Landmark-Hotel Finder API

## Contents

## Continuous Integration Workflow

Our project includes a GitHub Actions workflow designed to automate the build and test process whenever a pull request is made to the main branch. This workflow is defined in the `.github/workflows/build-and-test.yml` file.

Key Features
- Automated Build: The workflow triggers a build of the Maven project, ensuring that the project compiles successfully.

- Running Tests: All unit and integration tests are executed as part of the workflow. This helps catch potential issues early in the development process.

- Conditional Merging: The workflow is configured to enforce that all tests must pass before a pull request can be merged into the main branch. This ensures that the code in the main branch remains stable and functional.

This continuous integration setup helps maintain code quality and reduces the likelihood of bugs being introduced into the main branch. By automating these checks, we streamline the development process and encourage a consistent and reliable codebase.

For more details, please refer to the workflow file itself: `.github/workflows/build-and-test.yml`.

To configure your GitHub repo to enforce the status checks:

1) Navigate to Your Repository:

- Go to the GitHub repository where you want to set up the branch protection rule.

2) Access Repository Settings:

- Click on the "Settings" tab located at the top of the repository page.

3) Navigate to Branches:

- In the left sidebar, find and click on "Branches" under the "Code and automation" section.

4) Add Branch Protection Rule:

- Under the "Branch protection rules" section, click on "Add rule".

5) Specify Branch Name Pattern:

- In the "Branch name pattern" field, enter main.

6) Require Status Checks to Pass Before Merging:

- Check the box labeled "Require status checks to pass before merging."
- Select build-and-test from the list of available status checks will appear. 

7) Save Changes: 

- Click "Create" or "Save changes" to apply the branch protection rule.