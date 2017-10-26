Android-Architecture Guide Line
=========================


## 1. Naming Guideline

### 1) Resources
Follow this rule : `<What>_<Where>_<Description>_<Size/Color/State>`

- `Where` : The id of the view containing the resource. If it is used for multiple views, then `Where` should be `all`.

#### (1) Layout
`<Where>_<Description>`

ex) UserActivity - user.xml
    UserProfileFragment - user_profile.xml
    UserInfoFragment - user_info.xml
    UserProfileImageView - user_profile_image.xml
    UserInfoDetailFragment - user_info_detail.xml
    
#### (2) Strings
`<Where>_<Description>`

ex) 'confirm' - all_confirm
    'Sign In' - signin_title
    'Please enter email' - signin_emailhint

#### (3) Drawables
`<Where>_<Description>_<Size/Color/State>`

ex) all_ic_logo_red.png
    all_ic_logo_white.png
    all_ic_logo_small_white.png
    signin_btn_confirm.xml
    profile_btn_confirm_pressed.xml

Prefix | Usage | 
---|---|
ic | Icon |
btn | Button |

#### (4) IDs
`<What>_<Where>_<Description>`

Widget/View(Group) | Prefix | 
---|---|
LinearLayout | ll |
RelativeLayout | rl |
FrameLayout | fl |
ScrollView | sv |
RecyclerView | rv |
TextView | tv |
EditText | et |
Button | btn |
ImageView | iv |
ImageButton | ibtn |
ViewGroup | vg |
View | v |

#### (5) Dimensions
`<What>_<Where>_<Description>`

Prefix | Usage | 
---|---|
width	| width in dp
height	| height in dp
size	| if width == height
margin	| margin in dp
padding	| padding in dp
font    | font size in sp

#### (6) Colors
`<Where>_<Transparency>_<Brightness>_<Hue>`

Prefix | Explanation | 
---|---|
Transparency	| transparent
Brightness	    | light, original, dark (3 types)
Hue	            | red, orange, yellow, green, blue, navy, purple, black, white

ex) all_transparent_light_red

### 2) Java Classes

#### (1) Class member ordering
- Constants
- Fields
- Constructors
- Override methods (android lifecycle callbacks should be sequentially and consecutively)
- Public methods
- Private methods
- Inner classes

#### (2) Constants

Usage | Prefix
---|---|
SharedPreferences	| PREF_
Bundle	            | BUNDLE_
Fragment Arguments	| ARGUMENT_
Intent Extra	    | EXTRA_
Intent Action	    | ACTION_

- references
    - [A successful XML naming convention](https://jeroenmols.com/blog/2016/03/07/resourcenaming/)
    - [ribot/android-guidelines](https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md)
    